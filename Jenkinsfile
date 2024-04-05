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
                          credentialsId: 'nexuscr',
                          groupId: 'com.esprit.examen',
                          nexusUrl: '192.168.1.9:8081',
                          nexusVersion: 'nexus3',
                          protocol: 'http',
                          repository: 'nexus_tpachat',
                          version: '1.0'
                          }
                   }
            }

        stage('Build') {
            steps {
                sh 'docker build -t admin/TpAchat:latest .'
            }
        }

      

         stage("Login & Push"){
                     steps{
                         script{
                             withCredentials([string(credentialsId: 'tpAchat_Dockerhub', variable: 'tpAchat_Dockerhub')]) {
                                 sh 'echo "vagrant" | sudo -S docker login -u admin -p ${tpAchat_Dockerhub}'
                                 sh 'echo "vagrant" | sudo -S docker image push admin/TpAchat:latest'

                             }
                         }
                     }
                 }






    }
}
