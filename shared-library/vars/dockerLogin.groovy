def call() {
    script {
        withCredentials([usernamePassword(credentialsId: 'id2', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
            sh "echo ${DOCKER_PASS} | docker login -u ${DOCKER_USER} --password-stdin"
        }
    }
}
