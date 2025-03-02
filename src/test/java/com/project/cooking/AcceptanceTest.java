package com.project.cooking;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources/Features/",
    plugin = { "html:target/cucumber/wikipedia.html" },
    monochrome = true,
    snippets = SnippetType.CAMELCASE,
    glue = { "com.project.cooking" })

public class AcceptanceTest {

}