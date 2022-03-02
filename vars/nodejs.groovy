def call(){
    pipeline{
        agent{
            label "${BUILD_LABEL}"
        }
        //Auto trigger
//        triggers {
//            pollSCM('*/2 * * * *')
//        }
        stages{
            stage('check the code quality'){
                steps{
                    script{
                        common.sonarQube()
                    }
                }
            }
            stage('Lint checks'){
                steps{
                    sh '/home/centos/node_modules/eslint/bin/eslint.js .'
                }
            }
            stage('Test cases'){
                steps{
                    sh 'echo Test cases'
                }
            }

        }
        post {
            always {
                cleanWs()
            }
        }
    }
}
