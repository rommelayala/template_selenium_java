package com.example.aspects;

import io.cucumber.java.Scenario;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestStepStarted;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class TimingAspect {

    private final Map<Scenario, Long> scenarioStartTimeMap = new HashMap<>();
    private static Logger logger = LoggerFactory.getLogger(TimingAspect.class.getCanonicalName());

    @Before
    public void beforeScenario(Scenario scenario) {
        scenarioStartTimeMap.put(scenario, System.currentTimeMillis());
        System.out.println("Starting scenario: " + scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenarioStartTimeMap.containsKey(scenario)) {
            long startTime = scenarioStartTimeMap.remove(scenario);
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            System.out.println("Finished scenario: " + scenario.getName() + " in " + duration + " ms");
        }
    }

    private void handleTestStepStarted(TestStepStarted event) {
        // Handle step started event if necessary
    }

    private void handleTestRunFinished(TestRunFinished event) {
        // Optionally, perform any cleanup or final actions
    }
}
