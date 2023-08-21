package com.cps.template.cliapptemplate.api.impl

import org.junit.Rule
import org.springframework.boot.test.system.OutputCaptureRule
import picocli.CommandLine
import picocli.CommandLine.ExitCode
import spock.lang.Specification
import spock.util.environment.RestoreSystemProperties

import static java.lang.String.format

@RestoreSystemProperties
class EchoApiSpec extends Specification {

    @Rule
    OutputCaptureRule output = new OutputCaptureRule()

    EchoApi echoApi = Spy()

    CommandLine cmd

    void setup() {
        cmd = new CommandLine(echoApi)
    }

    def "echo message: \"#msgArgs\""() {
        given:
        String msg = String.join(System.lineSeparator(), msgArgs)

        when:
        int exitCode = cmd.execute("--message", msg)

        then:
        exitCode == ExitCode.OK
        output.out == format("%s%n", msg)
        output.err == ""

        where:
        msgArgs                            || _
        [""]                               || _
        ["a"]                              || _
        ["hello there"]                    || _
        ["hello", "there"]                 || _
        ["several", "lines", "of", "text"] || _
    }

    def "echo message - no message provided"() {
        given:
        def expectedFirstLine = "Missing required option: '--message=<message>'"

        when:
        int exitCode = cmd.execute()

        then:
        exitCode == ExitCode.USAGE
        output.out == ""
        output.err.readLines().get(0) == expectedFirstLine
    }

    def "echo message - app error"() {
        when:
        int exitCode = cmd.execute("--message", "something")

        then:
        1 * echoApi.start() >> { throw new IllegalStateException("Something went wrong") }

        exitCode == ExitCode.SOFTWARE
        output.out == ""
        output.err == format("Something went wrong%n")
    }
}
