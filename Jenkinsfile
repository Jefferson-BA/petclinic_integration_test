pipeline {

    agent any

    tools {
        maven 'Maven'
        jdk 'Java'
        // git 'Git'
    }

    stages {

        stage('Checkout') {
            steps {
                echo '📥 Getting code...'
                checkout scm
            }
        }

        // Nuevo Stage
        stage('Nuevo Stage') {
            steps {
                echo "Este es el Stage del Grupo 4"
            }
        }


        stage('Build') {
            steps {
                echo '🔨 Building...'
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                echo '🧪 Testing...'
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                echo '📦 Creating JAR...'
                sh 'mvn package -DskipTests'
            }
        }
    }
}
