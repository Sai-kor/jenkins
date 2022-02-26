// options example
//pipeline{
//    agent any
//    options { disableConcurrentBuilds() }
//    stages{
//        stage('one') {
//            steps{
//                sh 'sleep 10'
//            }
//        }
//    }
//}

// environement variable
pipeline{
    agent any
    environment {
        url = "google.com"
    }
    stages{
        stage('one') {
            steps{
                sh 'echo ${url}'
                echo url
            }
        }
    }
}