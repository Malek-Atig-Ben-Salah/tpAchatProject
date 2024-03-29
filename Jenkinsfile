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
                sh 'mvn clean install'
            }
        }
        stage('SONAR') {
            tools {
                jdk "jdk17" // the name you have given the JDK installation using the JDK manager (Global Tool Configuration)
            }
            environment {
                scannerHome = tool 'SonarQube Scanner'
                // the name you have given the Sonar Scanner (Global Tool Configuration)
            }
                steps {
                withSonarQubeEnv(installationName : 'tpAchatSonar'){
                    sh 'mvn clean sonar:sonar'
                }
            }
        }
    }
}
