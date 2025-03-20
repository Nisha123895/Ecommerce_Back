pipeline {
    agent any
    environment {
        AWS_REGION = 'ap-south-1'           // Your AWS Region
        EKS_CLUSTER = 'my-cluster'          // Your EKS Cluster name
        IMAGE_NAME = 'myapp'                // Docker Image name
        ECR_URL = '533267252835.dkr.ecr.ap-south-1.amazonaws.com'  // Your AWS ECR URL
    }
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Nisha123895/Ecommerce_Back.git'  // Your GitHub repository URL
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME:latest .'  // Build the Docker image
            }
        }
        stage('Push Image to AWS ECR') {
            steps {
                withCredentials([string(credentialsId: 'AWS_ECR_LOGIN', variable: 'AWS_ECR_PASS')]) {
                    sh '''
                    # Login to AWS ECR using the AWS CLI and push the Docker image
                    aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin $ECR_URL
                    docker tag $IMAGE_NAME:latest $ECR_URL/$IMAGE_NAME:latest
                    docker push $ECR_URL/$IMAGE_NAME:latest
                    '''
                }
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
                sh 'kubectl apply -f k8s/deployment.yaml'  // Deploy to Kubernetes using kubectl
            }
        }
    }
}
