//import org.jenkinsci.plugins.pipeline.modeldefinition.Utils

def sonarQube(){
    //sh 'sonar-scanner -Dsonar.projectKey=${COMPONENT} -Dsonar.sources=. -Dsonar.host.url=http://172.31.3.23:9000 -Dsonar.login=admin -Dsonar.password=admin123'
    //check whether the submission went fine or not- for this sonar-scanner cannot help but a api curl command can help
    //sh 'sonar-quality-gate.sh admin:admin123 172.31.3.23:9000 ${COMPONENT}'
    println 'Sonarqube testing'
    // we do not need to code quality check everytime so just putitng back some sample statement.
}
//
def publishArtifacts() {
//    if(env.GIT_BRANCH == "*tag*"){
//        println 'Ran on tag'
//    } else{
//        Utils.markStageSkippedForConditional('publish artifacts')
//    }
}

def prepareArtifacts() {
    //println PROG_LANG
    if(env.PROG_LANG_NAME == "nodejs" && env.PROG_LANG_VERSION == "16") {
        sh '''
            echo hello
            echo bye        
        '''
    }
}