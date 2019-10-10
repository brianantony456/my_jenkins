import jenkins.model.Jenkins
import hudson.security.ACL


//To avoid concurrency issue and prevent Jenkins from running jobs after a restart, start jenkins in quietdown mode.
// more details at https://www.praqma.com/stories/jenkins-quiet-startup/
Jenkins.instance.doQuietDown()

// Wake up after an async wait
Thread.start {
  // doCancelQuietDown requires admin privileges
  ACL.impersonate(ACL.SYSTEM)

  // Cancel quiet down mode automatically , after 1 min sleep.
  Thread.sleep(2 * 60 * 1000)
  Jenkins.instance.doCancelQuietDown()
}