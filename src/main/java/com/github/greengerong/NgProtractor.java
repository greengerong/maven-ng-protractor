/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2014-03-02                       *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/

package com.github.greengerong;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

import static com.google.common.base.Preconditions.checkNotNull;

@Mojo(name = "run", defaultPhase = LifecyclePhase.TEST)
public class NgProtractor extends AbstractMojo {

    @Parameter(property = "protractor", defaultValue = "protractor")
    private String protractor;

    @Parameter(property = "config", defaultValue = "${basedir}/protractor.conf.js", required = true)
    private File configFile;

    @Parameter(required = false)
    private boolean debug;

    @Parameter(property = "skipTests", required = false, defaultValue = "false")
    private Boolean skipTests;

    @Parameter(property = "skipProtractor", required = false, defaultValue = "false")
    private Boolean skipProtractor;

    @Parameter(property = "arguments", required = false)
    private String arguments;

    @Parameter(property = "ignoreFailed", required = false)
    private Boolean ignoreFailed;

    public void execute() throws MojoExecutionException {
        final Log log = getLog();
        if (skipProtractor || skipTests) {
            log.info("Skipping protractor test.");
            return;
        }
        try {
            checkNotNull(protractor, "Protractor should not be empty.");
            checkFileExists(configFile, "Protractor should be exists.");

            new ProtractorService(ignoreFailed, log).exec(new Command(protractor, configFile, debug, arguments));
        } catch (Exception e) {
            throw new MojoExecutionException("There were exceptions when run protractor test.", e);
        }
    }

    private File checkFileExists(File configFile, String message) {
        if (!configFile.exists()) {
            throw new RuntimeException(message);
        }
        return configFile;
    }
}
