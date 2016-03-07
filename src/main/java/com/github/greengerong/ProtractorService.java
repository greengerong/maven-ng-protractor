/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2014-03-02                       *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/

package com.github.greengerong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.maven.plugin.logging.Log;

public class ProtractorService {
    private boolean ignoreFailed;
    private final Log log;

    public ProtractorService(boolean ignoreFailed, Log log) {
        this.ignoreFailed = ignoreFailed;
        this.log = log;
    }

    public void exec(Command command) {
        final ProcessBuilder builder;
        builder = createProcessBuilder(command);
        log.info("Executing protractor test Suite ...");
        log.info(String.format("Command:%s", command.toString()));

        try {
            final Process process = builder.start();
            if (!executeProtractor(process)) {
                dealFailures();
            }
        } catch (IOException e) {
            log.error("Run protractor test error:", e);
            throw new RuntimeException(e);
        }
    }

    private void dealFailures() {
        if (ignoreFailed) {
            log.warn("There were  protractor test failures. But ignored the build.");
        } else {
            throw new RuntimeException("There were protractor test failures.");
        }
    }

    private boolean executeProtractor(final Process process) {

        BufferedReader protractorOutputReader = null;
        try {
            protractorOutputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            for (String line = protractorOutputReader.readLine(); line != null; line = protractorOutputReader.readLine()) {
                log.info(line);
            }
            return (process.waitFor() == 0);
        } catch (IOException e) {
            throw new RuntimeException("There was an error reading the output from protractor.", e);
        } catch (InterruptedException e) {
            throw new RuntimeException("The protractor process was interrupted.", e);
        } finally {
            closeQuietly(protractorOutputReader);
        }
    }

    private void closeQuietly(BufferedReader protractorOutputReader) {
        if (protractorOutputReader != null) {
            try {
                protractorOutputReader.close();
            } catch (IOException e) {
                log.error(e);
                throw new RuntimeException(e);
            }
        }
    }


	public ProcessBuilder createProcessBuilder(Command command) {
		ProcessBuilder builder;
		List<String> cmds = new ArrayList<String>();
		if (OSUtils.isWindows()) {
			cmds.add("cmd.exe");
			cmds.add("/C");
			cmds.add(command.getProtractor());
			cmds.addAll(command.getCommand());
			builder = new ProcessBuilder(cmds);
		} else {
			cmds.add(command.getProtractor());
			cmds.addAll(command.getCommand());
			builder = new ProcessBuilder(cmds);
		}
		builder.redirectErrorStream(true);
		return builder;
	}

}
