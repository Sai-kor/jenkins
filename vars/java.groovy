def call(){
    pipeline{
        agent{
            label "${BUILD_LABEL}"
        }
        //Auto trigger
        triggers {
            pollSCM('*/2 * * * *')
        }
        stages{
            stage('compile the code'){
                steps {
                    sh 'echo compile the ${COMPONENT} code'
                }
            }
            stage('check the code quality'){
                steps{
                    sh 'echo check the code quality'
                }
            }
            stage('Lint checks'){
                steps{
                    sh ' echo Lint checks'
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
                cleanws()
            }
        }
    }
}
