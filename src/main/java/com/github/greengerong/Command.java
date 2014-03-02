/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2014-03-02                       *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/

package com.github.greengerong;

import org.apache.commons.lang.StringUtils;

import java.io.File;

public class Command {
    private String protractor;
    private final File configFile;
    private final boolean debug;
    private String arguments;

    public Command(String protractor, File configFile, boolean debug, String arguments) {
        this.protractor = protractor;
        this.configFile = configFile;
        this.debug = debug;
        this.arguments = arguments;
    }

    public File getConfigFile() {
        return configFile;
    }

    public boolean isDebug() {
        return debug;
    }

    public String getProtractor() {
        return protractor;
    }

    public String getArguments() {
        return arguments;
    }

    @Override
    public String toString() {
        return getCommand();
    }

    private String getCommand() {
        return String.format("%s %s %s",
                debug ? "debug" : "",
                configFile.getAbsolutePath(),
                StringUtils.isBlank(arguments) ? "" : arguments).trim();
    }
}
