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
//pipeline{
//    agent any
//    environment {
//        url = "google.com"
//        SSH = credentials("CENTOS")
//        ssh1= credentials('pswd')
//    }
//    stages{
//        stage('one') {
//            environment {
//        url = "google.com"
//            }
//            steps{
//                sh 'echo ${url}'
//                echo url
//                sh 'env'
//                echo SSH
//                sh 'echo ${ssh1} | base64'
//            }
//        }
//    }
//}

//parameters example
//pipeline {
//    agent any
//    parameters {
//        string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
//
//        text(name: 'BIOGRAPHY', defaultValue: '', description: 'Enter some information about the person')
//
//        booleanParam(name: 'TOGGLE', defaultValue: true, description: 'Toggle this value')
//
//        choice(name: 'CHOICE', choices: ['One', 'Two', 'Three'], description: 'Pick something')
//
//        password(name: 'PASSWORD', defaultValue: 'SECRET', description: 'Enter a password')
//    }
//    stages {
//        stage('Example') {
//            steps {
//                echo "Hello ${params.PERSON}"
//
//                echo "Biography: ${params.BIOGRAPHY}"
//
//                echo "Toggle: ${params.TOGGLE}"
//
//                echo "Choice: ${params.CHOICE}"
//
//                echo "Password: ${params.PASSWORD}"
//            }
//        }
//    }
//}

//tools
//pipeline{
//    agent any
//    tools{
//        maven 'maven-3.8.4'
//    }
//    stages{
//        stage('Maven'){
//            steps{
//                sh 'mvn --version'
//            }
//        }
//    }
//}

////input example,mostly used for approvals
//pipeline {
//    agent any
//    stages {
//        stage('Example') {
//            input {
//                message "Should we continue?"
//                ok "Yes, we should."
//                submitter "sai,bob"
//                parameters {
//                    string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
//                }
//            }
//            steps {
//                echo "Hello, ${PERSON}, nice to meet you."
//            }
//        }
//    }
//}
//when condition exmaple

pipeline{
    agent any
    parameters{
        choice(name: 'ENV', choices: ['DEV', 'PROD' ],description:'choose ENV')
    }
    stages{
        stage('DEV'){
            when {
                environment name: 'DEV', value: 'DEV'
            }
            steps{
                echo 'DEV'
            }
        }
        stage('PROD'){
            when {
               environment name: 'PROD', value: 'PROD'
            }
            steps{
                echo 'PROD'
            }
        }

    }
}