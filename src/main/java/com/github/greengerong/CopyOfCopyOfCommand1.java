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
import java.util.ArrayList;
import java.util.List;

public class CopyOfCopyOfCommand1 {
    private String protractor;
    private final File configFile;
    private boolean debugBrk;
    private final boolean debug;
    private String arguments;

    public CopyOfCopyOfCommand1(String protractor, File configFile, boolean debugBrk, boolean debug, String arguments) {
        this.protractor = protractor;
        this.configFile = configFile;
        this.debugBrk = debugBrk;
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

    public List<String> getCommand() {
          List<String> cmds = new ArrayList<String>();
          String debugCmd = debug ? (debugBrk ? "--debug-brk" : "debug") : "";

          if (!StringUtils.isBlank(debugCmd)) {
                  cmds.add(debugCmd);
          }

          cmds.add(configFile.getAbsolutePath());

          if (!StringUtils.isBlank(arguments)) {
                  cmds.add(arguments);
          }
          return cmds;
    }

}
