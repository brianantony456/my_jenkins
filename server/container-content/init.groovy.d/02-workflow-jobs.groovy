import jenkins.*
import jenkins.model.*
import hudson.*
import hudson.model.*
import hudson.tasks.LogRotator
import hudson.plugins.git.*;
import org.jenkinsci.plugins.workflow.flow.FlowDefinition
import org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition
import org.jenkinsci.plugins.workflow.job.WorkflowJob


private GitSCM gitConfiguration(){
    def git_repo = "https://github.com/brianantony456/docker-demo.git"
    GitSCM scm = new GitSCM(git_repo)
    String branch = "refs/heads/master"
    scm.branches = [new BranchSpec(branch)];
    return scm
}

private Boolean jobExists(job_name){
    boolean job_exists = Hudson.instance.items.name.contains(job_name)
    return job_exists
}

private WorkflowJob create(String job_name){
    println "Now creating ${job_name}"
    job = Jenkins.instance.createProject(WorkflowJob, job_name)
    scm = gitConfiguration()
    FlowDefinition flowDefinition = new CpsScmFlowDefinition(scm, "job_dsl/${job_name}")
    job.definition = flowDefinition
    return job
}


private createNodeTestPipeline(){
   if (!jobExists("nodejs_job")){
          job = create('nodejs_job')
          String cron_spec = "TZ=Europe/Berlin\n" +
                        "H 8,10,13,16 * * 1-5 ";
          hudson.triggers.TimerTrigger newCron = new hudson.triggers.TimerTrigger(cron_spec);
          newCron.start(job, true);
          job.addTrigger(newCron);
          job.save()
   }
}

createNodeTestPipeline()