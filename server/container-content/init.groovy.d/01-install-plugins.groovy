import jenkins.model.*

/* Install required plugins and restart Jenkins, if necessary.  */
final List<String> REQUIRED_PLUGINS = [
    "pam-auth","jackson2-api","jsch","pipeline-input-step","workflow-cps-global-lib","build-timeout","cloudbees-folder","durable-task","workflow-job","docker-workflow","token-macro","scm-api","momentjs","structs","github-branch-source","display-url-api","resource-disposer","pipeline-github-lib","workflow-cps","docker-build-publish","jquery-detached","gradle","ssh-credentials","credentials-binding","authentication-tokens","workflow-api","pipeline-stage-tags-metadata","pipeline-model-declarative-agent","docker-commons","workflow-basic-steps","github","ldap","slack","pipeline-rest-api","bouncycastle-api","pipeline-milestone-step","workflow-step-api","credentials","timestamper","lockable-resources","git","workflow-durable-task-step","git-client","workflow-multibranch","nodejs","mailer","git-server","mapdb-api","apache-httpcomponents-client-4-api","branch-api","pipeline-graph-analysis","workflow-scm-step","config-file-provider","script-security","ssh-slaves","trilead-api","icon-shim","ace-editor","workflow-support","matrix-auth","matrix-project","workflow-aggregator","pipeline-build-step","handlebars","pipeline-model-definition","job-dsl","command-launcher","pipeline-model-extensions","pipeline-model-api","antisamy-markup-formatter","github-api","plain-credentials","pipeline-stage-view","github-pullrequest","ws-cleanup","pipeline-npm","pipeline-stage-step","jdk-tool"
]
if (Jenkins.instance.pluginManager.plugins.collect {
        it.shortName
    }.intersect(REQUIRED_PLUGINS).size() != REQUIRED_PLUGINS.size()) {
    REQUIRED_PLUGINS.collect {
        Jenkins.instance.updateCenter.getPlugin(it).deploy()
    }.each {
        it.get()
    }
    Jenkins.instance.restart()
    println 'Run this script again after restarting to create the jobs!'
    throw new RestartRequiredException(null)
}

println "All plugins are installed"

