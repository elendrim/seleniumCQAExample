package com.github.elendrim.seleniumcqaexemple.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import seleniumcqa.WebDriverBot;
import seleniumcqa.WebDriverBotImpl;

public class SeleniumWebFormTest {

	@Test
	public void eightComponents() {

		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(0));

		WebDriverBot webDriverBot = new WebDriverBotImpl(driver);
		webDriverBot.visit("https://www.selenium.dev/selenium/web/web-form.html");

		String title = webDriverBot.getTitle();
		assertEquals("Web form", title);

		webDriverBot.find(By.name("my-text")).sendKeys("Selenium");
		webDriverBot.find(By.cssSelector("button")).click();

		String value = webDriverBot.find(By.id("message")).getText();

		assertEquals("Received!", value);

		driver.quit();
	}

}
