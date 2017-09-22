/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2014-03-02                       *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/

package com.github.greengerong;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Command {

    public static List<String> splitArguments(String arguments) {
        List<String> result = new ArrayList<String>();
        if (!arguments.trim().isEmpty()) {
            String[] argumantArray = arguments.split(" ", 0);
            String quotedPart = null;
            for (String arg : argumantArray) {
                if (quotedPart != null) {
                    quotedPart += " " + arg;
                    int count = 0;
                    int idx = -1;
                    while ((idx = arg.indexOf('"', idx+1)) >= 0) {
                        ++count;
                    }
                    if ((count % 2) == 1) {
                        result.add(quotedPart);
                        quotedPart = null;
                    }
                } else {
                    int count = 0;
                    int idx = -1;
                    while ((idx = arg.indexOf('"', idx+1)) >= 0) {
                        ++count;
                    }
                    if ((count % 2) == 0) {
                        if (!arg.isEmpty()) {
                            result.add(arg);
                        }
                    } else {
                        quotedPart = arg;
                    }
                }
            }
        }
        return result;
    }

    private String protractor;
    private final File configFile;
    private boolean debugBrk;
    private final boolean debug;
    private List<String> arguments;

    public Command(String protractor, File configFile, boolean debugBrk, boolean debug, String arguments) {
        this.protractor = protractor;
        this.configFile = configFile;
        this.debugBrk = debugBrk;
        this.debug = debug;
        this.arguments = splitArguments(arguments);
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

    public List<String> getArguments() {
        return arguments;
    }

    @Override
    public String toString() {
        return getCommand();
    }

    private String getCommand() {
        StringBuilder commandLineBuilder = new StringBuilder();
        if (debug) {
            commandLineBuilder.append(debugBrk ? "--debug-brk" : "debug")
                    .append(" ");
        }
        commandLineBuilder.append(configFile.getAbsolutePath());
        for (String argument : arguments) {
            commandLineBuilder.append(" ")
                    .append(argument);
        }
        return commandLineBuilder.toString();
    }
}
