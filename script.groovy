def buildJar() {
    echo "building the application..."
    sh 'mvn package'
} 
def testApp() {
    echo "test in the app"
    sh 'mvn test'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t ravikiran1994/docker:jma-2.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push ravikiran1994/docker:jma-2.0'
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this
