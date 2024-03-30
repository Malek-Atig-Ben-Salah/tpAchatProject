pipeline {

    agent any

    stages {
        stage('GIT CHECKOUT') {
            steps {
                git branch: 'master', url: 'https://github.com/Malek-Atig-Ben-Salah/tpAchatProject.git'
            }
        }
        stage('SONARQUBE ANALYSIS') {
            steps {
                withSonarQubeEnv(installationName: 'tpAchatSonar') {
                    sh 'mvn clean sonar:sonar -Dsonar.java.binaries=src/target/classes'
                }
            }
        }
        stage('QUALITY GATE STATUS') {
            steps {
                script {
                    waitForQualityGate abortPipeline: false, credentialsId: 'jenkins-sonar'
                }
            }
        }
    }
}
