package com.cps.template.cliapptemplate

import com.cps.template.cliapptemplate.provider.PicocliProvider
import org.junit.Rule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.system.OutputCaptureRule
import org.springframework.test.context.ContextConfiguration
import picocli.CommandLine
import picocli.CommandLine.IFactory
import spock.lang.Specification
import spock.util.environment.RestoreSystemProperties

import static java.lang.String.format

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, properties = [
    "info.name=APP_NAME", "info.version=APP_VERSION", "info.executable-name=app"
])
@ContextConfiguration
@RestoreSystemProperties
class AppSpec extends Specification {
    private static final String USAGE_MESSAGE = """\
    Usage: app [-hV] [COMMAND]
      -h, --help      Show this help message and exit.
      -V, --version   Print version information and exit.
    Commands:
      echo  -> displays a provided message in standard output
    """.stripIndent().replaceAll("\n", System.lineSeparator())

    @Rule
    OutputCaptureRule output = new OutputCaptureRule()

    @Autowired
    IFactory factory

    @Autowired
    PicocliProvider picocliProvider

    CommandLine cmd

    void setup() {
        cmd = new CommandLine(new App(), factory).setCommandName(picocliProvider.getExecutableName())
    }

    def "used without subcommand"() {
        given:
        String expectedErrMessage = format("Missing required subcommand%n$USAGE_MESSAGE")

        when:
        int exitCode = cmd.execute()

        then:
        noExceptionThrown()
        exitCode == CommandLine.ExitCode.USAGE
        output.out == ""
        output.err == expectedErrMessage
    }

    def "help message"() {
        given:
        String expectedOutMessage = USAGE_MESSAGE

        when:
        int exitCode = cmd.execute(args)

        then:
        noExceptionThrown()
        exitCode == CommandLine.ExitCode.OK
        output.out == expectedOutMessage
        output.err == ""

        where:
        args                   | _
        ["--help"] as String[] | _
        ["-h"] as String[]     | _
    }

    def "version message"() {
        when:
        int exitCode = cmd.execute(args)

        then:
        noExceptionThrown()
        exitCode == CommandLine.ExitCode.OK
        output.out == format("APP_NAME - version APP_VERSION%n")
        output.err == ""

        where:
        args                      | _
        ["--version"] as String[] | _
        ["-V"] as String[]        | _
    }
}
