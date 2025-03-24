package win2025project;









import org.junit.runner.RunWith;   


import org.junit.*;
import cucumber.*;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;




@RunWith(Cucumber.class)
@CucumberOptions(
        features="test_case",
        plugin= {"summary","html:target/cucmber/report.html"},
        monochrome=true,
        snippets=SnippetType.CAMELCASE,
        glue="win2024project"
        
        )





public class project {

}
