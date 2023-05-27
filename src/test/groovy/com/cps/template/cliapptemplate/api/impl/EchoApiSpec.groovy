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
        given:
        def expected = """\
            Missing required option: '--message=<message>'
            Usage: echo [-hV] -m=<message>
            -> displays a provided message in standard output
              -h, --help                Show this help message and exit.
              -m, --message=<message>   Message to print
              -V, --version             Print version information and exit.\
        """.stripIndent().replaceAll("\n", System.lineSeparator())

        when:
        int exitCode = cmd.execute()

        then:
        exitCode == ExitCode.USAGE
        output.err.trim() == expected
    }
}
