job('NodeJS Docker example') {
    scm {
        git('git@github.com:brianantony456/my_jenkins.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('Brian Antony Dsouza')
            node / gitConfigEmail('brianantony456@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    // wrappers {
    //     nodejs('nodejs') // this is the name of the NodeJS installation in 
    //                      // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    // }
    steps {
        dockerBuildAndPublish {
            repositoryName('brianantony456/node-test')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}