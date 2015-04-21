@Grab(group='com.spotify', module='pipeline-conventions', version='1.0.3-SNAPSHOT')
import com.spotify.pipeline.Pipeline
import javaposse.jobdsl.dsl.Job


new Pipeline(this) {{ build {
  // TODO(negz): ALF-2117 Notify committer only when the build breaks.

  group(name: 'Test') {
    jenkinsPipeline.inJob {
      jenkinsPipeline.inSteps {
        shell(readFileFromWorkspace('.spotify/pipelines/tox.sh'))
      }
    }
  }
  group(name: 'PyPi Build & Upload') {
    jenkinsPipeline.inJob {
      jenkinsPipeline.inSteps {
        shell(readFileFromWorkspace('.spotify/pipelines/release.sh'))
      }
    }
  }

}}}
