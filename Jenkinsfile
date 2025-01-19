pipeline {
    agent {
        docker {
            image 'gradle:7.2-jdk11'
            args '-v $HOME/.gradle:/root/.gradle'
        }
    }

    environment {
        // Define environment variables
        GRADLE_HOME = tool 'Gradle'
        PATH = "${GRADLE_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                // Get code from repository
                checkout scm
            }
        }

        stage('Start Selenium Grid') {
            steps {
                script {
                    // Start Selenium Grid using docker-compose
                    sh 'docker-compose -f docker-compose.yml up -d'
                    // Wait for Grid to be ready
                    sh 'sleep 30'
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    try {
                        // Run Gradle tests
                        sh './gradlew clean test'
                    } finally {
                        // Capture test results and reports
                        junit '**/build/test-results/test/*.xml'

                        // If you're using Allure reports
                        allure([
                            includeProperties: false,
                            jdk: '',
                            properties: [],
                            reportBuildPolicy: 'ALWAYS',
                            results: [[path: 'build/allure-results']]
                        ])
                    }
                }
            }
        }
    }

    post {
        always {
            script {
                // Stop Selenium Grid
                sh 'docker-compose -f docker-compose.yml down'

                // Archive test reports
                archiveArtifacts artifacts: 'build/reports/**/*', allowEmptyArchive: true

                // Clean up Docker
                sh 'docker system prune -f'
            }
        }

        success {
            echo 'Tests completed successfully!'
        }

        failure {
            echo 'Tests failed!'
        }
    }
}
