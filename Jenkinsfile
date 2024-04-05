pipeline {

    agent any
    environment {
    DOCKERHUB_CREDENTIALS = credentialsId('TpAchat-Docker')
    }
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
                    nexusArtifactUploader artifacts: [
                        [
                            artifactId: 'tpAchatProject',
                            classifier: '',
                            file: 'target/tpAchatProject-1.0.jar',
                            type: 'jar'
                        ]
                    ],
                    credentialsId: 'nexus_cred',
                    groupId: 'com.esprit.examen',
                    nexusUrl: '172.20.10.11:8081',
                    nexusVersion: 'nexus3',
                    protocol: 'http',
                    repository: 'TP_Achat_Nexus',
                    version: '1.0'
                }
            }
        }

    stage('build') {
         steps {
           sh 'docker build -t admin/TpAchat:latest .'
         }

    }
    stage('Login') {
         steps {
         sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
        }
 }

      stage('Push') {
           steps {
           sh 'docker push admin/TpAchat:latest'
            }
     }





    }


}
