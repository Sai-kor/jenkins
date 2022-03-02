def call(){
    pipeline{
        agent{
            label "${BUILD_LABEL}"
        }
        //Auto trigger
        triggers {
            pollSCM('H/2 * * * *')
        }
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
         //           sh '/home/centos/node_modules/eslint/bin/eslint.js *.js'
                    // developers will look for errors in lint checks adn correct them for code styling
                    sh 'echo lint cases'
                }
            }
            //unit test cases- Developers best practice
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
