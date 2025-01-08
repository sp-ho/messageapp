pipeline {
    agent any

    // gcp project id
    environment {
        GCP_PROJECT = 'employee1-447012'
    }

    stages {
        stage('Clone Repository') {
            steps {
                // github url
                git 'https://github.com/sp-ho/employee1.git'
            }
        }

        stage('Build Application') {
            steps {
                bat './mvnw clean package'
            }
        }

        stage('Test Application') {
            steps {
                bat './mvnw test'
            }
        }

        stage('Deploy to App Engine') {
            steps {
                withCredentials([file(credentialsId: 'sa-key', variable: 'GOOGLE_APPLICATION_CREDENTIALS')]) {
                    bat '''
                        gcloud auth activate-service-account --key-file=%GOOGLE_APPLICATION_CREDENTIALS%
                        gcloud config set project %GCP_PROJECT%
                        gcloud app deploy app.yaml --quiet
                    '''
                }
            }
        }
    }

    post {
        success {
            echo 'Application deployed successfully!'
        }
        failure {
            echo 'Deployment failed. Please check the logs.'
        }
    }
}
