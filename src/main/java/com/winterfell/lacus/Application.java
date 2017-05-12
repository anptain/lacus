package com.winterfell.lacus;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		// File file = new File("C:/Selenium/iexploredriver.exe");
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		// JavascriptExecutor jse = (JavascriptExecutor)driver;
		// List<WebElement> elements =
		// (List<WebElement>)jse.executeScript("return jQuery.find"
		// +"('.tm-qx-picBox a:eq(0)')");
		// elements.get(0).getAttribute("href");
		driver.get("http://www.lvmama.com/tuangou/sale-654704");
		List<WebElement> qg = driver.findElements(By.cssSelector(".ft-price-box-inner > ul > li"));
		for (WebElement webElement : qg) {
			WebElement priceInfo = webElement.findElement(By.cssSelector(".price-info"));
			if (priceInfo != null && !priceInfo.getText().trim().equals("")) {
				webElement.click();
				break;
			}
		}
		driver.findElements(By.cssSelector("a[href*='javascript:beforeSubmit']")).get(0).click();
		// driver.findElement(By.className("").findElement(context)).getAttribute(arg0)
		// SpringApplication.run(Application.class, args);
	}
}

