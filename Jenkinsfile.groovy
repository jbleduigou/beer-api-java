node {
    def mvnHome
    stage('Preparation') {
        git 'git@github.com:jbleduigou/beer-api-java.git'
        mvnHome = tool 'M3'
    }
    stage('Build') {
        // Run the maven build
        if (isUnix()) {
            sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
        } else {
            bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
        }
    }
    stage('Results') {
        junit '**/target/surefire-reports/TEST-*.xml'
        archiveArtifacts 'target/*.jar'
        step([$class: 'JacocoPublisher', execPattern: '**/*.exec'])
    }
    /*
    stage('Docker Image') {
        dir('docker/images/imfapi') {
            sh "make"
        }
    }
    stage('Docker Compose') {
        dir('docker/environment') {
            sh "make"
            sh "docker ps -a"
            sleep 30
            sh "curl http://localhost:8081/actuator/health"
            sh "docker ps -a"
            sh "make down"
        }
    }
     */
}
