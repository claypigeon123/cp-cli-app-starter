package com.cp.template.cliapptemplate.api.impl;

import com.cp.template.cliapptemplate.api.Api;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.PrintWriter;

@Component
@Command(
    name = "echo",
    description = "-> displays a provided message in standard output",
    mixinStandardHelpOptions = true
)
public class EchoApi extends Api {

    private PrintWriter pw;

    @Option(
        names = { "-m", "--message" },
        description = "Message to print",
        required = true
    )
    private String message;

    @Override
    protected void initialize() {
        pw = new PrintWriter(System.out);
    }

    @Override
    protected void start() {
        pw.println(message);
    }

    @Override
    protected void close() {
        pw.flush();
        pw.close();
    }
}
