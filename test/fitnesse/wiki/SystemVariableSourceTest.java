// Copyright (C) 2003-2009 by Object Mentor, Inc. All rights reserved.
// Released under the terms of the CPL Common Public License version 1.0.

package fitnesse.wiki;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SystemVariableSourceTest {

  @Test
  public void shouldReadProvidedProperties() {
    Properties properties = new Properties();
    properties.setProperty("TestProp", "found");

    SystemVariableSource source = new SystemVariableSource(properties);

    assertThat(source.findVariable("TestProp").getValue(), is("found"));
  }

  @Test
  public void systemPropertyShouldTakePrecedenceOverProvidedProperties() {
    Properties properties = new Properties();
    properties.setProperty("user.name", "xxxxx");

    SystemVariableSource source = new SystemVariableSource(properties);

    assertThat(source.findVariable("user.name").getValue(), is(System.getProperty("user.name")));
  }

  @Test
  public void environmentVariableShouldTakePrecedenceOverSystemProperties() {
    Properties properties = new Properties();
    System.setProperty("PATH", "xxxxx");

    SystemVariableSource source = new SystemVariableSource(properties);

    assertThat(source.getProperty("PATH"), is(System.getenv("PATH")));
  }

  @Test
  public void urlVariableShouldTakePrecedenceOverEnvironmentAndSystemProperties() {
    Properties properties = new Properties();
    System.setProperty("PATH", "xxxxx");

    SystemVariableSource source = new SystemVariableSource(properties);
    Map<String,Object> urlVariables = new HashMap<String,Object>();
    urlVariables.put("PATH", "yyyyy");
    source.setUrlParams(urlVariables);

    assertThat(source.getProperty("PATH"), is(urlVariables.get("PATH")));
  }

}
