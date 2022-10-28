package com.cp.template.mavenprojecttemplate.api;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public abstract class Api implements Runnable {

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
}
