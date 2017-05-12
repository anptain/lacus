package com.winterfell.lacus;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Abcd {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url;
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		url = "https://login.lvmama.com/nsso/login?service=http%3A%2F%2Fwww.lvmama.com%2F%23";

		// 设置全局的等待，在定位元素时，对所有元素设置超时时间
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// 打开驴妈妈登录页
		driver.get(url + "/");
		// 在输入用户名和密码
		driver.findElement(By.id("usernameOrNum")).sendKeys("13916961770");
		driver.findElement(By.id("passwordNum")).sendKeys("xiaer94up.");
		// 点击登录按钮
		driver.findElement(By.id("loginButton")).click();

		try {
			// 点击登录按钮后，等待3秒钟

			// 进入特卖会页面
			String url2 = "http://www.lvmama.com/tuangou";
			driver.navigate().to(url2);

			// driver转换成JavascriptExecutor对象
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			// jquery选择器 获取 .tm-qx-picBox 样式下的第一个a标签
//			List<WebElement> elements = (List<WebElement>) jse
//					.executeScript("return jQuery.find" + "('.tm-qx-picBox a:eq(0)')");
			
			
			List<WebElement> elements = driver.findElements(By.cssSelector(".tm-qx-picBox a"));
			
			// 获取a标签的链接
			driver.navigate().to(elements.get(0).getAttribute("href"));
			
			
			
			 List<WebElement> ls = driver.findElements(By.cssSelector(".ft-price-box-inner >ul >li"));
			 for (WebElement webElement : ls) {
			 WebElement priceInfo =
			 webElement.findElement(By.cssSelector(".price-info"));
			 if (priceInfo != null && !priceInfo.getText().trim().equals(""))
			 {
			 webElement.click();
			 break;
			 }
			 }
			 driver.findElement(By.cssSelector("input[name='contact.email']")).sendKeys("625@qq.com");
			 
			 driver.findElements(By.cssSelector("a[href*='javascript:beforeSubmit']")).get(0).click();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
