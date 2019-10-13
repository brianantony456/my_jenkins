import com.cloudbees.plugins.credentials.impl.*;
import com.cloudbees.plugins.credentials.*;
import com.cloudbees.plugins.credentials.domains.*;


Credentials c = (Credentials) new UsernamePasswordCredentialsImpl('docker_hub', 'docker_hub', "brianantony456", '')
SystemCredentialsProvider.getInstance().getStore().addCredentials(Domain.global(), c)