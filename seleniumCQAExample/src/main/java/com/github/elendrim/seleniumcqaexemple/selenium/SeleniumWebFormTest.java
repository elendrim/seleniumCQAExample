package com.github.elendrim.seleniumcqaexemple.selenium;

import static com.github.elendrim.seleniumcqa.AssertFunctions.ASSERT_EQUALS;
import static com.github.elendrim.seleniumcqa.AssertFunctions.ASSERT_EQUALS_IGNORE_CASE;
import static com.github.elendrim.seleniumcqa.GetFunctions.GET_TEXT;
import static com.github.elendrim.seleniumcqa.GetFunctions.GET_TITLE;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.github.elendrim.seleniumcqa.Configuration;
import com.github.elendrim.seleniumcqa.WebDriverBot;
import com.github.elendrim.seleniumcqa.WebDriverBotImpl;

public class SeleniumWebFormTest {

	private WebDriver driver;

	@BeforeEach
	public void before() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(0));
		driver.manage().window().maximize();
	}

	@AfterEach
	public void after() {
		driver.quit();
	}

	@Test
	public void eightComponents() {

		Configuration configuration = new Configuration();
		configuration.setHighlightSleepDuration(Duration.ofSeconds(1));
		WebDriverBot webDriverBot = new WebDriverBotImpl(driver, configuration);
		webDriverBot.visit("https://www.selenium.dev/selenium/web/web-form.html");

		webDriverBot.should("web form", GET_TITLE, ASSERT_EQUALS_IGNORE_CASE);

		webDriverBot.find(By.name("my-text")).sendKeys("Selenium");
		webDriverBot.find(By.cssSelector("button")).click();

		webDriverBot.find(By.id("message")).should("Received!", GET_TEXT, ASSERT_EQUALS);

	}

}
