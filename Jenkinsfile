pipeline {

    agent any

    stages {
        stage('Display Date') {
            steps {
                script {
                    def currentDate = sh(script: 'date', returnStdout: true).trim()
                    echo "Current Date: ${currentDate}"
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
        stage('NEXUS') {
            steps {
                   script {
                          nexusArtifactUploader artifacts:
                          [
                            [
                             artifactId: 'tpAchatProject',
                             classifier: '',
                             file: 'target/tpAchatProject-1.0.jar',
                             type: 'jar'
                             ]
                          ],
                          credentialsId: 'nexus-auth-v1',
                          groupId: 'com.esprit.examen',
                          nexusUrl: '172.20.10.3:8081',
                          nexusVersion: 'nexus3',
                          protocol: 'http',
                          repository: 'maven-snapshots-emna',
                          version: '1.0'
                          }
                   }
            }

    }
}
