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

// environment example
pipeline{
    agent any
    environment {
        url = "google.com"
        SSH = credentials(CENTOS)
    }
    stages{
        stage('one') {
            steps{
                sh 'echo ${url}'
                echo url
                echo SSH
            }
        }
    }
}