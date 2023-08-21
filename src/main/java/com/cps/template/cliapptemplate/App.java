package com.cps.template.cliapptemplate;

import com.cps.template.cliapptemplate.api.impl.EchoApi;
import com.cps.template.cliapptemplate.provider.PicocliProvider;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(
    versionProvider = PicocliProvider.class,
    mixinStandardHelpOptions = true,
    subcommands = {
        EchoApi.class
    }
)
public class App {
}
