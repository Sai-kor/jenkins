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

    sh '''
        curl -f -v -u ${NEXUS_USR}:${NEXUS_PSW} --upload-file ${COMPONENT}-${gitTag}.zip http://nexus.devops.internal:8081/repository/${COMPONENT}/${COMPONENT}-${gitTag}.zip
        
    '''
}

def prepareArtifacts() {
        //println PROG_LANG
    if(env.PROG_LANG_NAME == "nodejs" && env.PROG_LANG_VERSION == "16") {
        sh '''
           npm install
           zip -r ${COMPONENT}-${gitTag}.zip node_modules server.js  
         '''
    }
        //  ls -ltr   ;;  these 2 are the files that are actually needed by our application to run.This is our actual core application,the rest of the files whatever added is unecessary files

        if(env.PROG_LANG_NAME == "java" && env.PROG_LANG_VERSION == "1.8") {
            sh '''
              mvn clean package   
              mv target/${COMPONENT}-1.0.jar  ${COMPONENT}.jar
              zip -r ${COMPONENT}-${gitTag}.zip ${COMPONENT}.jar
            '''
        }
    if(env.PROG_LANG_NAME == "python" && env.PROG_LANG_VERSION == "3") {
        sh '''
              zip -r ${COMPONENT}-${gitTag}.zip requirements.txt *.py ${COMPONENT}.ini
            '''
    }
    if(env.PROG_LANG_NAME == "golang" && env.PROG_LANG_VERSION == "1.16") {
        sh '''
          go mod init dispatch
          go get
          go build
          zip -r ${COMPONENT}-${gitTag}.zip ${COMPONENT}
        '''
    }

}