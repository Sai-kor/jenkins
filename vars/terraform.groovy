def call(){
    pipeline{
        agent{
            label "${BUILD_LABEL}"
        }

        environment{

        }
        parameters {
            choice(name: 'ENVIRONMENT', choices: ['', 'Dev', 'Prod'], description: 'Pick Environment')
            choice(name: 'ACTION', choices: ['', 'apply', 'destroy'], description: 'Pick Terraform Action')
        }
        stages{
            stage('Label Builds'){
                steps{
                    script{
                        env.gitTag= GIT_BRANCH.split('/').last()  // if shell has to access it, then it should be env variable ,env works anywhere in pipeline as declared as env.
                        //def gitTag= sh([returnStdout: true, script: 'echo ${GIT_BRANCH} | awk -F / "{print $NF}"' ])
                        addShortText background: 'white', borderColor: 'white', color: 'red', link: '', text: "${ENVIRONMENT}"
                        addShortText background: 'white', borderColor: 'white', color: 'red', link: '', text: "${ACTION}"

                    }
                }
            }
            stage('Apply Terraform Action'){
                steps{
                    sh '''
                        terraform init -backend-config=./env/${ENVIRONMENT}-backend.tfvars
                         terraform ${ACTION} -auto-approve -var-file=env/${ENVIRONMENT}.tfvars
                    '''
                }
            }
   x

        }
        post {
            always {
                cleanWs()
            }
        }
    }
}
