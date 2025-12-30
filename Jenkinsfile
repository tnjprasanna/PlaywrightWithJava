pipeline {
    agent any
    
    tools {
        maven 'Apache_Maven'
    }
    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/tnjprasanna/PlaywrightWithJava'
            }
        }
        
        stage('Build') {
            steps {
                bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        
        stage("Deploy to QA") {
            steps {
                echo "deploy to qa"
            }
        }
        
        stage('Regression Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_regressions.xml"
                }
            }
        }
        
        stage('Publish Extent Report') {
            steps {
                publishHTML([allowMissing: false,
                             alwaysLinkToLastBuild: false,
                             keepAll: true,
                             reportDir: 'build',
                             reportFiles: 'TestExecutionReport.html',
                             reportName: 'HTML Extent Report',
                             reportTitles: ''])
            }
        }
    }
}