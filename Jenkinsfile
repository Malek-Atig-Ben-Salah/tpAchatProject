pipeline {

    agent any

    stages {

        stage('GIT CHECKOUT') {
            steps {
                git branch: 'brancheEya', url: 'https://github.com/Malek-Atig-Ben-Salah/tpAchatProject.git'
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
                sh 'mvn clean install -DskipTests'
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
                            nexusUrl: 'localhost:8081',
                            nexusVersion: 'nexus3',
                            protocol: 'http',
                            repository: 'tpAchatProject-Release',
                            version: '1.0'
                }
            }
        }


    }
}
