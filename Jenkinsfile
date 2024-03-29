pipeline {

    agent any

    stages {
        stage('GIT CHECKOUT') {
            steps {
                git branch: 'master', url: 'https://github.com/Malek-Atig-Ben-Salah/tpAchatProject.git'
            }
        }
        stage('MAVEN BUILD') {
            steps {
                echo 'java -version'
                sh 'mvn clean install'
            }
        }
        stage('SONAR') {
                steps {
                withSonarQubeEnv(installationName : 'tpAchatSonar'){
                    sh 'mvn clean org.sonarsource.scanner.maven:sonar-maven-plugin:sonar'
                }
            }
        }
    }
}
