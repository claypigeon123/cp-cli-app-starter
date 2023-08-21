package com.cps.template.cliapptemplate.provider;

import com.cps.template.cliapptemplate.config.props.InfoProperties;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import picocli.CommandLine.IVersionProvider;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class PicocliProvider implements IVersionProvider {

    @NonNull
    private final InfoProperties infoProperties;

    public String getExecutableName() {
        return infoProperties.executableName();
    }

    @Override
    public String[] getVersion() {
        String name = infoProperties.name();
        String version = infoProperties.version().split("-SNAPSHOT")[0];

        return new String[] { format("%s - version %s", name, version) };
    }
}
