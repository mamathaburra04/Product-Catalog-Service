pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Build') {
      steps {
        sh 'mvn -B clean package -DskipTests'
      }
      post {
        success {
          echo 'Build succeeded'
          archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
        failure {
          echo 'Build failed'
        }
      }
    }
  }
}


