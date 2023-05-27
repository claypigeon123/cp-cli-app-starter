package com.cps.template.cliapptemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@RequiredArgsConstructor
@Command(name = "app", version = "1.0.0", mixinStandardHelpOptions = true)
public class App {
}
