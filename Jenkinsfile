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
                git branch: 'Emna_Final', url: 'https://github.com/Malek-Atig-Ben-Salah/tpAchatProject.git'
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
                          sh 'docker build -t emnaarfaoui/tpachat:latest .'
                      }
                  }
              }






            stage("Login & Push Docker Hub"){
                        steps{
                            script{
                                withCredentials([string(credentialsId: 'docker', variable: 'docker')]) {
                                    sh 'docker login -u emnaarfaoui -p ${docker}'
                                    sh 'docker image push emnaarfaoui/tpachat:latest'

                                }
                            }
                        }
                    }


            stage('Docker compose') {
                        steps {
                            script{
                                sh 'docker compose restart'
                                sh 'docker compose up -d'
                            }
                        }
                    }
            stage('Grafana') {
                  steps {
                        echo 'Hello grafana'
                  }
            }

            stage('Actualisation du tableau de bord Grafana') {
                steps {
                    script {
                        // Exemple : Actualisation du tableau de bord Grafana avec des informations sur le dernier build
                        sh 'curl -X POST -H "Content-Type: application/json" -d \'{"build_status": "success", "build_number": "123", "author": "John Doe"}\' http://grafana-server/api/dashboards/update'
                    }
                }
            }


    }
}
