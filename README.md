
#### maven-ng-protractor
=========================

 This maven plugin for Angular E2E test [protractor](https://github.com/angular/protractor/blob/master/docs/getting-started.md).

## How to use this plugin:

        <plugin>
            <groupId>com.github.greengerong</groupId>
            <artifactId>maven-ng-protractor</artifactId>
            <version>0.0.1</version>
            <configuration>
            </configuration>
            <executions>
                <execution>
                    <id>ng-protractor</id>
                    <phase>test</phase>
                    <goals>
                        <goal>run</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>

   You can see it under the demo fold. You can go there, then run "mvn clean install" to see the result.