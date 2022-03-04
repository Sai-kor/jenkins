def call(){
    pipeline{
        agent{
            label "${BUILD_LABEL}"
        }
        //Auto trigger
//        triggers {
//            pollSCM('H/2 * * * *')
//        }

        environment{
            PROG_LANG_NAME = "java" // can declare & run specific version also
            PROG_LANG_VERSION = "1.8"
            NEXUS = credentials('NEXUS')
        }
        stages{
            stage('Label Builds'){
                steps{
                    script{
                        env.gitTag= GIT_BRANCH.split('/').last()  // if shell has to access it, then it should be env variable ,env works anywhere in pipeline as declared as env.
                        //def gitTag= sh([returnStdout: true, script: 'echo ${GIT_BRANCH} | awk -F / "{print $NF}"' ])
                        addShortText background: 'white', borderColor: 'white', color: 'red', link: '', text: "${gitTag}"
                    }
                }
            }
            stage('compile the code'){
                steps {
                    sh 'mvn compile'
                }
            }
            stage('check the code quality'){
                steps{
                    script{
                        common.sonarQube()
                    }
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
            stage('publish artifacts'){
                when {
                    expression { sh([returnStdout: true, script: 'echo ${GIT_BRANCH} | grep tags || true']) }
                }
                steps{
                    script {
                        common.prepareArtifacts()
                        common.publishArtifacts()
                    }
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
