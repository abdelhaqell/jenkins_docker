@Library('shared-library@main') _

pipeline {
    agent any
    
    tools {
        maven 'mvn-3.9.6'
    }

    environment {
        // Docker Hub username :
        DOCKER_REGISTRY = 'abdelhaqelattar2002'
        // Docker image tag :
        DOCKER_TAG = 'v1.0.0' 
        // Docker image name :
        DOCKER_IMAGE_NAME = 'java-mvn-app' 
    }

    stages {
        stage('Checkout Code') {
            steps {
                getcheckoutgroovy(
                    branch: 'main',
                    url:'https://github.com/abdelhaqell/test_jenkins.git'
                )
            }
        }

        stage('Build') {
            steps {
                sh '''
                    cd $WORKSPACE
                    pwd
                    ls 
                    mvn clean install
                '''
            }
        }

        stage('Generate Artifact') {
            steps {
                sh '''
                    cd $WORKSPACE
                    mvn package
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                    cd $WORKSPACE
                    docker build -t ${DOCKER_REGISTRY}/${DOCKER_IMAGE_NAME}:${DOCKER_TAG} .
                '''
            }
        }

        stage('Login to Docker Registry') {
            steps {
                dockerLogin(credentialsId: 'id2')  // Passing the credentialsId to the function
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    sh "docker push ${DOCKER_REGISTRY}/${DOCKER_IMAGE_NAME}:${DOCKER_TAG}"
                }
            }
        }

        stage('Cleanup') {
            steps {
                sh "docker rmi ${DOCKER_REGISTRY}/${DOCKER_IMAGE_NAME}:${DOCKER_TAG}"
            }
        }
    }
}
