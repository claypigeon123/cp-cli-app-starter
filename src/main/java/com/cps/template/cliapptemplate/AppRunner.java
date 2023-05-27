package com.cps.template.cliapptemplate;

import com.cps.template.cliapptemplate.api.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import picocli.CommandLine;

import java.util.Collection;

@SpringBootApplication
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner, ExitCodeGenerator {
    private final App app;
    private final Collection<Api> apis;
    private final CommandLine.IFactory factory;

    private int exitCode = 0;

    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(AppRunner.class, args)));
    }

    @Override
    public void run(String... args) {
        CommandLine cmd = new CommandLine(app, factory);
        apis.forEach(cmd::addSubcommand);
        exitCode = cmd.execute(args);
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }
}
