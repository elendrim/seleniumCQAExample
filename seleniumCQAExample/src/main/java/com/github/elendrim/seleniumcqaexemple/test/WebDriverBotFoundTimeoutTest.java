package com.github.elendrim.seleniumcqaexemple.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seleniumcqa.AssertText.ASSERT_EQUALS;
import static seleniumcqa.AssertText.GET_TITLE;

import java.time.Duration;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import seleniumcqa.WebDriverBot;
import seleniumcqa.WebDriverBotImpl;

public class WebDriverBotFoundTimeoutTest {

	@ParameterizedTest
	@ArgumentsSource(WebDriverArgumentsProvider.class)
	public void timeoutExceptionOnElementNotFound(WebDriver driver) {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofMillis(0));
			driver.manage().window().maximize();
			
			WebDriverBot webDriverBot = new WebDriverBotImpl(driver);
			webDriverBot.visit("https://www.selenium.dev/selenium/web/web-form.html");
	
			webDriverBot.should("Web form", GET_TITLE, ASSERT_EQUALS);
	  		
	  		
			TimeoutException thrown = assertThrows(
	  	           TimeoutException.class,
	  	           () -> webDriverBot.find(By.id("element-not-present")),
	  	           "Expected find to throw timeoutException, but it didn't"
	  	    );
			
			assertEquals("Expected condition failed: waiting for presence of element located by: By.id: element-not-present (tried for 10 second(s) with 500 milliseconds interval)", thrown.getMessage());
		}finally {
			driver.quit();
		}
	}
	
	
}
