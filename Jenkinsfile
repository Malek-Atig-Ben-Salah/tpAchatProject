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


              stage('Build image') {
                  steps {
                      script {
                          // Exécution de la construction Docker sans utiliser sudo
                          sh 'docker build -t admin/tpachat:latest .'
                      }
                  }
              }




             stage("Login & Push image"){
                 steps{
                     script{
                         withCredentials([string(credentialsId: 'tpachat_docker', variable: 'tpachat_docker')]) {
                             // Afficher le nom d'utilisateur
                             echo "Nom d'utilisateur : ${env.tpachat_docker_USR}"

                             // Afficher le mot de passe
                             echo "Mot de passe : ${env.tpachat_docker_PSW}"

                             // Connexion et push de l'image Docker
                             sh 'docker login -u ${env.tpachat_docker_USR} -p ${env.tpachat_docker_PSW}'
                             sh 'docker image push admin/tpachat:latest'
                         }
                     }
                 }
             }











    }
}
