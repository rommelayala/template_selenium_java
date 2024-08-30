package com.example.properties;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("file:src/test/resources/${env}_environment.properties" )
public interface EnvironmentProperties extends Config {
    String url();

    String username();

    String password();

    String toto();
}
