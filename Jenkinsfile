pipeline {

    agent any

    stages {
        stage('GIT CHECKOUT') {
            steps{
                echo 'checkout branch '
                git branch : 'master' , url : 'https://github.com/Malek-Atig-Ben-Salah/tpAchatProject.git'
            }
        }
    }
}