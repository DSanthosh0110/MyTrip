package com.MyTrip.MyTrip;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MyTrip {
	public static WebDriver driver = null;

	public static void browserLaunch() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	public static void handleFrame() {
		WebElement frame = driver.findElement(By.xpath("//iframe[@title='notification-frame-~55854522']"));
		driver.switchTo().frame(frame);
		driver.findElement(By.xpath("//a[@id='webklipper-publisher-widget-container-notification-close-div']")).click();
	}

	public static void fromCity() throws InterruptedException {
		driver.findElement(By.xpath("//li[@data-cy='account']")).click(); // login
		driver.findElement(By.xpath("//div[@class='fsw_inputBox searchCity inactiveWidget ']")).click(); // from city
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@type='text' and @autocomplete='off']")).sendKeys("Chennai");
		Thread.sleep(2000);
		driver.findElement(By.id("react-autowhatever-1-section-0-item-0")).click();
	}

	public static void toCity() throws InterruptedException {
		driver.findElement(By.xpath("//div[@class='fsw_inputBox searchToCity inactiveWidget ']")).click(); // to city
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@type='text' and @autocomplete='off']")).sendKeys("kolkata");
		Thread.sleep(2000);
		driver.findElement(By.id("react-autowhatever-1-section-0-item-0")).click();
	}

	public static void tripDate(String day, String month, String date, String year) {
		driver.findElement(By.xpath("//div[@class='fsw_inputBox dates inactiveWidget ']")).click();
//		driver.findElement(By.xpath("//div[@aria-label='Sun Jan 15 2023']")).click();
		driver.findElement(By.xpath("//div[@aria-label='" + day + " " + month + " " + date + " " + year + "']"))
				.click();
	}

	public static void journeyClass(String noOfAdult, String noOfChildren, String noOfInfants) {
		driver.findElement(By.xpath("//div[@class='fsw_inputBox flightTravllers inactiveWidget ']")).click();
		driver.findElement(By.xpath("//li[@data-cy='adults-" + noOfAdult + "']")).click();
		driver.findElement(By.xpath("//li[@data-cy='children-" + noOfChildren + "']")).click();
		driver.findElement(By.xpath("//li[@data-cy='infants-" + noOfInfants + "']")).click();
		driver.findElement(By.xpath("//li[@data-cy='travelClass-2']")).click(); // Business class
		driver.findElement(By.xpath("//button[@data-cy='travellerApplyBtn']")).click(); // Apply
		driver.findElement(By.xpath("//a[@class='primaryBtn font24 latoBold widgetSearchBtn ']")).click(); // search

	}

	static void threads() throws InterruptedException {
		Thread.sleep(6000);
	}

	public static void quit() {
		driver.quit();
	}

	public static void main(String[] args) throws InterruptedException {
		browserLaunch();
		handleFrame();
		fromCity();
		toCity();
		tripDate("Mon", "Jan", "16", "2023");
		journeyClass("2", "1", "0");
		threads();
		quit();
	}
}
