pipeline {
    agent any

    tools {
        gradle 'Gradle'  // Make sure you have Gradle configured in Jenkins tools
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Clean and build the project
                sh 'gradle clean build -x test'
            }
        }

        stage('Test') {
            steps {
                // Run tests
                sh 'gradle test'
            }
            post {
                always {
                    // Publish test results
                    junit '**/build/test-results/test/*.xml'
                }
            }
        }
    }

    post {
        success {
            echo 'Build and tests completed successfully!'
        }
        failure {
            echo 'Build or tests failed!'
        }
    }
}
