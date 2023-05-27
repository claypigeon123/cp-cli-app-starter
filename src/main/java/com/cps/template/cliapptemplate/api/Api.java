package com.cps.template.cliapptemplate.api;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import picocli.CommandLine.IExitCodeGenerator;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public abstract class Api implements Runnable, IExitCodeGenerator {

    protected int exitCode = 0;

    protected void initialize() {}

    protected abstract void start();

    protected void close() {}

    @Override
    public final void run() {
        initialize();
        start();
        close();
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }
}
