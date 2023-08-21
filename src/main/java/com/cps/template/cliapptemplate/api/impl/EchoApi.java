package com.cps.template.cliapptemplate.api.impl;

import com.cps.template.cliapptemplate.api.Api;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Component
@EqualsAndHashCode(callSuper = true)
@Command(name = "echo", description = "-> displays a provided message in standard output", mixinStandardHelpOptions = true)
public class EchoApi extends Api {

    @Option(names = { "-m", "--message" }, description = "Message to print", required = true)
    private String message;

    @Override
    protected void start() {
        System.out.println(message);
    }
}
