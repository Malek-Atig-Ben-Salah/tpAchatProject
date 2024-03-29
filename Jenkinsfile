pipeline {

    agent any

    stages {
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
                sh 'mvn clean install'
            }
        }
        stage('SONAR') {
            steps {
                withSonarQubeEnv(installationName : 'tpAchatSonar'){
                    sh 'mvn clean sonar:sonar'
                }
            }
        }
    }
}
