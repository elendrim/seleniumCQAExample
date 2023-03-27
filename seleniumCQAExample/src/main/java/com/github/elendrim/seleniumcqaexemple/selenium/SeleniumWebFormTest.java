package com.github.elendrim.seleniumcqaexemple.selenium;

import static seleniumcqa.AssertText.ASSERT_EQUALS;
import static seleniumcqa.AssertText.ASSERT_EQUALS_IGNORE_CASE;
import static seleniumcqa.AssertText.GET_TEXT;
import static seleniumcqa.AssertText.GET_TITLE;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import seleniumcqa.WebDriverBot;
import seleniumcqa.WebDriverBotImpl;

public class SeleniumWebFormTest {

	private WebDriver driver;
	
	@BeforeEach
	public void before() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(0));
		driver.manage().window().maximize();
	}
	
	@AfterEach
	public void after() {
		driver.quit();
	}
	
	
	@Test
	public void eightComponents() {

		WebDriverBot webDriverBot = new WebDriverBotImpl(driver);
		webDriverBot.visit("https://www.selenium.dev/selenium/web/web-form.html");

		webDriverBot.should("web form", GET_TITLE, ASSERT_EQUALS_IGNORE_CASE);

		webDriverBot.find(By.name("my-text")).sendKeys("Selenium");
		webDriverBot.find(By.cssSelector("button")).click();
		
		webDriverBot.find(By.id("message")).should("Received!", GET_TEXT, ASSERT_EQUALS);
		
	}

}
