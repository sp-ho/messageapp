pipeline {
    agent any

    environment {
        GCP_PROJECT = 'employee1-447012'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git 'https://github.com/sp-ho/employee_management1.git'
            }
        }

        stage('Build Application') {
            steps {
                sh './mvnw clean package'
            }
        }

        stage('Test Application') {
            steps {
                sh './mvnw test'
            }
        }

        stage('Deploy to App Engine') {
            steps {
                withCredentials([file(credentialsId: 'gcp-service-account-key', variable: 'GOOGLE_APPLICATION_CREDENTIALS')]) {
                    sh '''
                        gcloud auth activate-service-account --key-file=$GOOGLE_APPLICATION_CREDENTIALS
                        gcloud config set project $GCP_PROJECT
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
