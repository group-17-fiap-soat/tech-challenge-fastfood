pipeline {
    agent any

    parameters {
        string(name: 'AWS_REGION', defaultValue: 'us-east-1')
        string(name: 'CLUSTER_NAME', defaultValue: 'fastfood-app-eks')
        string(name: 'ECR_REPO', defaultValue: '711772164085.dkr.ecr.us-east-1.amazonaws.com/tech-challenge-fastfood')
        string(name: 'IMAGE_TAG', defaultValue: 'latest')
    }

    environment {
        IMAGE_URI = "${ECR_REPO}:${IMAGE_TAG}"
    }

    stages {
        stage('Checkout Código') {
            steps {
                git url: 'https://github.com/group-17-fiap-soat/tech-challenge-fastfood.git', branch: 'main'
            }
        }

        stage('Build e Push da Imagem') {
            steps {
                sh '''
                    docker build -t ${IMAGE_URI} .
                    aws ecr get-login-password --region ${AWS_REGION} | docker login --username AWS --password-stdin ${ECR_REPO}
                    docker push ${IMAGE_URI}
                '''
            }
        }

        stage('Deploy no EKS') {
            steps {
                sh '''
                    aws eks update-kubeconfig --region ${AWS_REGION} --name ${CLUSTER_NAME}
                    kubectl apply -f k8s/
                '''
            }
        }
    }
}
