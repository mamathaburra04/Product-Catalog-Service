pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Build') {
      steps {
        // use absolute path to maven to avoid PATH issues for Jenkins service
        sh '/opt/homebrew/bin/mvn -B clean package -DskipTests'
      }
      post {
        success {
          archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
      }
    }

    stage('Deploy') {
      steps {
        script {
          sh 'mkdir -p /tmp/product-catalog-deploy'
        }

        sh "pkill -f 'product-catalog' || true"

        sh 'cp target/*.jar /tmp/product-catalog-deploy/product-catalog.jar'

        sh "nohup java -jar /tmp/product-catalog-deploy/product-catalog.jar > /tmp/product-catalog-deploy/app.log 2>&1 &"

        sh 'sleep 3 || true'
        sh 'echo \"--- last 80 lines of app.log ---\"'
        sh 'tail -n 80 /tmp/product-catalog-deploy/app.log || true'
      }
    }
  }
}
