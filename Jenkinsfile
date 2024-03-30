pipeline {

    agent any

    stages {
        stage('Display Date') {
            steps {
                script {
                    def currentDate = currentBuild.getTimestamp()
                    def formattedDate = currentDate.format("yyyy-MM-dd HH:mm:ss")
                    echo "Formatted Date: ${formattedDate}"
                }
            }
        }
        stage('GIT CHECKOUT') {
            steps {
                git branch: 'master', url: 'https://github.com/Malek-Atig-Ben-Salah/tpAchatProject.git'
            }
        }
        stage('UNIT TESTS') {
            steps {
                sh 'mvn test'
            }
        }
        stage('MAVEN BUILD') {
            steps {
                echo 'java -version'
                sh 'mvn clean install'
            }
        }
        stage('SONARQUBE ANALYSIS') {
            steps {
                withSonarQubeEnv(installationName: 'tpAchatSonar') {
                    sh 'mvn clean sonar:sonar'
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
