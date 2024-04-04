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
        stage('NEXUS') {
            steps {
                script {
                    nexusArtifactUploader artifacts:
                            [
                                [
                                        artifactId: 'tpAchatProject',
                                        classifier: '',
                                        file: 'target/tpAchatProject-1.0.jar',
                                        type: 'jar']
                            ],
                            credentialsId: 'nexus-auth',
                            groupId: 'com.esprit.examen',
                            nexusUrl: '192.168.33.10:8081/',
                            nexusVersion: 'nexus3',
                            protocol: 'http',
                            repository: 'tpAchatProject-Release',
                            version: '1.0'
                }
            }
        }
    }
}
