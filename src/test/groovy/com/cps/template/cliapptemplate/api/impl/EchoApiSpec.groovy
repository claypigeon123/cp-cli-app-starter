package com.cps.template.cliapptemplate.api.impl


import org.junit.Rule
import org.springframework.boot.test.system.OutputCaptureRule
import picocli.CommandLine
import picocli.CommandLine.ExitCode
import spock.lang.Specification
import spock.util.environment.RestoreSystemProperties

@RestoreSystemProperties
class EchoApiSpec extends Specification {

    @Rule
    OutputCaptureRule output = new OutputCaptureRule()

    CommandLine cmd

    void setup() {
        cmd = new CommandLine(new EchoApi())
    }

    def "verify echo to standard output with message \"#msg\""() {
        when:
        int exitCode = cmd.execute("--message", msg)

        then:
        exitCode == ExitCode.OK
        output.out.trim() == msg

        where:
        msg                        || _
        ""                         || _
        "a"                        || _
        "hello there"              || _
        "hello\nthere"             || _
        "several\nlines\nof\ntext" || _
    }

    def "verify fails if no message provided"() {
        when:
        int exitCode = cmd.execute()

        then:
        exitCode == ExitCode.USAGE
        output.err.trim().contains("Missing required option: '--message=<message>'")
    }
}
