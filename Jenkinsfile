pipeline {
    agent any
    tools {
        maven 'apache-maven-3.5.2'
        jdk 'jdk1.8.0_131'
    }
    stages {
	    stage ('Build') {
            steps {
		bat 'mvn install'
            }
            post {
                success {
                    junit 'target/surefire-reports/*.xml' 
                }
            }
        }
	stage('cobertura'){
		steps{
			bat 'mvn cobertura:cobertura -Dcobertura.report.format=xml'
		}
		post{
			success {
				step([$class: 'CoberturaPublisher', autoUpdateHealth: false, autoUpdateStability: false, coberturaReportFile: 'target/site/cobertura/*.xml', failUnhealthy: false, failUnstable: false, maxNumberOfBuilds: 0, onlyStable: false, sourceEncoding: 'ASCII', zoomCoverageChart: false])
			}
		}
	}
	
     }
}
