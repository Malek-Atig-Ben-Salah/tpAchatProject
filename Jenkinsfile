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
        stage('SONARQUBE ANALYSIS') {
            steps {
                withSonarQubeEnv(installationName: 'tpAchatSonar') {
                    sh 'mvn clean sonar:sonar'
                }
            }
        }
        stage('QUALITY GATE STATUS') {
            steps {
                script {
                    waitForQualityGate abortPipeline: false, credentialsId: 'jenkins-sonar'
                }
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
                            nexusUrl: '192.168.33.10:8081',
                            nexusVersion: 'nexus3',
                            protocol: 'http',
                            repository: 'tpAchatProject-Release',
                            version: '1.0'
                }
            }
        }
        stage('BUILDING IMAGE') {
            steps {
                script{
                    sh 'echo "vagrant" | sudo -S docker image build -t tpachat:v1.$BUILD_ID .'
                    sh 'echo "vagrant" | sudo -S docker image tag tpachat:v1.$BUILD_ID malekbensalah/tpachat:v1.$BUILD_ID'
                    sh 'echo "vagrant" | sudo -S docker image tag tpachat:v1.$BUILD_ID malekbensalah/tpachat:latest'
                }
            }
        }
        stage("PUSH THE IMAGE TO DOCKER HUB"){
            steps{
                script{
                    withCredentials([string(credentialsId: 'docker_hub', variable: 'docker_hub')]) {
                        sh 'echo "vagrant" | sudo -S docker login -u malekbensalah -p ${docker_hub}'
                        sh 'echo "vagrant" | sudo -S docker image push malekbensalah/tpachat:v1.$BUILD_ID'
                        sh 'echo "vagrant" | sudo -S docker image push malekbensalah/tpachat:latest'
                    }
                }
            }
        }
        stage('DOCKER COMPOSE') {
            steps {
                script{
                    sh 'echo "vagrant" | sudo -S docker compose up'
                    sh 'echo "application up "'
                }
            }
        }
        stage('GRAFANA') {
            steps {
                script{
                    sh 'echo "application up "'
                }
            }
        }
    }
}
