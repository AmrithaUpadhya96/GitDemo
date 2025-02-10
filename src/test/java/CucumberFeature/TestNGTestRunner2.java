package CucumberFeature;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features="src/test/java/CucumberFeature",glue="StepDefination",
monochrome=true,tags="@Smoke",plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner2 extends AbstractTestNGCucumberTests {

}