def getDockerTag() {
    def tag = sh script: 'git rev-parse HEAD', returnStdout: true
    return tag
}

pipeline {
    agent {
        labels = 'Rentown'
    }
    environment{
        Docker_tag = getDockerTag()
    }
    
    stages{

        stage('Quality Gate Status Chect') {
            agent {
                docker {
                    image 'maven'
                    args '-v $HOME/ .m2:/root..m2'
                }

                steps{
                    script{
                        withSonarQubeEnv ('sonarserver') {
                            
                        }
                    }
                }
            }


        }
    }

}