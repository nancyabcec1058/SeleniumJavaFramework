package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src\\test\\java\\Cucumber",
                  glue="stepDefinition",
                  monochrome=true,
                  tags= "@Regression",
                  plugin= {"html:target/cucumber.html",
                		  "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})
public class TestNGRunner extends AbstractTestNGCucumberTests{

}
