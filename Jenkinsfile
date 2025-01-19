pipeline {

    agent any

    environment {
        GRADLE_HOME = tool 'Gradle'
        PATH = "/usr/local/bin:${GRADLE_HOME}/bin:${env.PATH}"  // Add /usr/local/bin to PATH
        DOCKER_PATH = "/Applications/Docker.app/Contents/Resources/bin"  // Docker binary location on Mac
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
                    sh '/usr/local/bin/docker-compose -f docker-compose.yml up -d'
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
