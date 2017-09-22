/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2014-03-02                       *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/

package com.github.greengerong;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;

public class CommandTest {
    
    private Map<String, String[]> testData = new HashMap<String, String[]>();
    
    public CommandTest() {
        createTestData(" ", "--param.foo=C:\\some\\windows\\path", "--param.bar=something-different");
        createTestData(" ", "--param.foo=\"C:\\some spaced\\path\"", "--param.bar=something-different");
        createTestData("    ", "--param.foo=C:\\some\\windows\\path", "--param.bar=something-different");
        createTestData("    ", "--param.foo=\"C:\\some spaced\\path\"", "--param.bar=something-different");
    }

    private void createTestData(String concat, String... args) {
        StringBuilder test = new StringBuilder();
        if (args != null) {
            for (String arg : args) {
                if (test.length() > 0) {
                    test.append(concat);
                }
                test.append(arg);
            }
        }
        testData.put(test.toString(), args);
    }

    /**
     * Test of splitArguments method, of class Command.
     */
    @Test
    public void testSplitArguments() {
        for (Entry<String, String[]> test : testData.entrySet()) {
            System.out.println("splitArguments(\"" + test.getKey() +"\")");
            List<String> arguments = Command.splitArguments(test.getKey());
            Assert.assertNotNull(arguments);
            Assert.assertTrue("expected "+ test.getValue().length + " arguments put found " + arguments.size(), test.getValue().length == arguments.size());
            Assert.assertArrayEquals(test.getValue(), arguments.toArray(new String[arguments.size()]));
        }
    }
}
