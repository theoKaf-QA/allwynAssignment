pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'JDK17'
    }

    parameters {
        choice(name: 'ENVIRONMENT', choices: ['test', 'staging', 'prod'], description: 'Select test environment')
        string(name: 'BASE_URI', defaultValue: 'https://fakerestapi.azurewebsites.net', description: 'API Base URI')
    }

    environment {
        ALLURE_VERSION = '2.30.0'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code from repository...'
                checkout scm
            }
        }

        stage('Clean Workspace') {
            steps {
                echo 'Cleaning workspace...'
                sh 'mvn clean'
            }
        }

        stage('Compile') {
            steps {
                echo 'Compiling the project...'
                sh 'mvn compile'
            }
        }

        stage('Run Tests') {
            steps {
                echo "Running API tests on ${params.ENVIRONMENT} environment..."
                script {
                    try {
                        sh """
                            mvn test
//                             \
//                             -Dbase.uri=${params.BASE_URI} \
//                             -Dtest.environment=${params.ENVIRONMENT}
                        """
                    } catch (Exception e) {
                        echo "Tests failed, but continuing to generate reports..."
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }

        stage('Generate Allure Report') {
            steps {
                echo 'Generating Allure test report...'
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'target/allure-results']]
                    ])
                }
            }
        }

        stage('Publish Test Results') {
            steps {
                echo 'Publishing TestNG results...'
                junit testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true
            }
        }
    }

    post {
        always {
            echo 'Cleaning up workspace...'
            cleanWs()
        }

        success {
            echo 'Pipeline executed successfully!'
            emailext (
                subject: "SUCCESS: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: """
                    <p>SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'</p>
                    <p>Check console output at <a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a></p>
                    <p>View Allure Report: <a href='${env.BUILD_URL}allure'>Allure Report</a></p>
                """,
                to: '${DEFAULT_RECIPIENTS}',
                mimeType: 'text/html'
            )
        }

        failure {
            echo 'Pipeline failed!'
            emailext (
                subject: "FAILURE: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: """
                    <p>FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'</p>
                    <p>Check console output at <a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a></p>
                    <p>View Allure Report: <a href='${env.BUILD_URL}allure'>Allure Report</a></p>
                """,
                to: '${DEFAULT_RECIPIENTS}',
                mimeType: 'text/html'
            )
        }

        unstable {
            echo 'Some tests failed, but build completed'
            emailext (
                subject: "UNSTABLE: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: """
                    <p>UNSTABLE: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'</p>
                    <p>Some tests failed. Check console output at <a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a></p>
                    <p>View Allure Report: <a href='${env.BUILD_URL}allure'>Allure Report</a></p>
                """,
                to: '${DEFAULT_RECIPIENTS}',
                mimeType: 'text/html'
            )
        }
    }
}