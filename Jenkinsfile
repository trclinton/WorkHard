pipeline {
    agent any

    tools {
        gradle '7.6'  // Use the exact name of your Gradle installation
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'gradle clean build -x test'
            }
        }

        stage('Test') {
            steps {
                sh 'gradle test'
            }
            post {
                always {
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
