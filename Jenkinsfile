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

stage('Build image yes') {
    steps {
        script {
            // Exécution de la construction Docker sans utiliser sudo
            sh 'docker build -t admin/tpachat .'
        }
    }
}
stage("Login & Push image ") {
    steps {
        script {
            withCredentials([string(credentialsId: 'tpachat-Docker', variable: 'tpachat-Docker')]) {
                sh 'docker login -u admin -p ${tpachat-Docker}'
                sh 'docker image push admin/tpachat:latest'
            }
        }
    }
}




    }


}
