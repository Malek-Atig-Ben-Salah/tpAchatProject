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
                    def nexusUrl = 'http://172.20.0.3:8081'
                    def credentialsId = 'nexus-auth-v1'
                    def groupId = 'com.esprit.examen'
                    def repository = 'maven-releases'
                    def version = '1.0'
                    def artifactId = 'tpAchatProject'
                    def classifier = ''
                    def type = 'jar'
                    def file = 'target/tpAchatProject-1.0.jar'

                    echo "Nexus URL: ${nexusUrl}"
                    echo "Credentials ID: ${credentialsId}"
                    echo "Group ID: ${groupId}"
                    echo "Repository: ${repository}"
                    echo "Version: ${version}"
                    echo "Artifact ID: ${artifactId}"
                    echo "Classifier: ${classifier}"
                    echo "Type: ${type}"
                    echo "File: ${file}"

                    nexusArtifactUploader artifacts: [[
                        artifactId: artifactId,
                        classifier: classifier,
                        file: file,
                        type: type
                    ]],
                    credentialsId: credentialsId,
                    groupId: groupId,
                    nexusUrl: nexusUrl,
                    nexusVersion: 'nexus3',
                    protocol: 'http',
                    repository: repository,
                    version: version
                }
            }
        }
    }
}
