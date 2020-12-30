package MyAssignment3;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Lexus {

	public static void main(String[] args) {
		
		
		// 1). Go to lexus.com
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumFiles\\browserDrivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(); 
		driver.get("http://www.lexus.com/");

		
		
		
		// 2). Verify the title of the page contains “Experience Amazing”. 
		String actualTitle = driver.getTitle();
		String expectedTitle = "Experience Amazing";
		
		if(actualTitle.contains(expectedTitle)) {
			System.out.println("Pass title check");
		}else {
			System.out.println("Fail title check");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		
		
		//3). Click on Do not Sell My Personal information at the bottom of the page.
		driver.findElement(By.xpath("//a[@href=\"https://privacy.toyota.com/\"]")).click();
		
		
		
		
		// 4). Verify the page title contains “Privacy Hub”.
		 String parentWindow = driver.getWindowHandle();
		   Set<String> windowHandles = driver.getWindowHandles();
		for(String handle: windowHandles) {
			if(!handle.equals(parentWindow)) {
				driver.switchTo().window(handle);
			}
		}         			
		String actualTitle2 = driver.getTitle();
		String expectedTitle2 = "Privacy Hub";
		
//		System.out.println(actualTitle2);
		if(actualTitle2.contains(expectedTitle2)) {
			System.out.println("Pass title check on second page");
		}else {
			System.out.println("Fail title check on second page");
		}
		
		
		
		
		// 5). Click on Your Privacy Rights.
		driver.findElement(By.xpath("//img[@src='images/icon2.png']")).click();
		
		
		
		
		// 6). Verify that the page url is “https://privacy.toyota.com/privacy-hub/privacyright.html”.
        Iterator<String> iterator = windowHandles.iterator();
		iterator.next();
		driver.switchTo().window(iterator.next());
		
		String expectedUrl = "https://privacy.toyota.com/privacy-hub/privacyright.html";
		String currentUrl = driver.getCurrentUrl();
		if(currentUrl.contains(expectedUrl)) {
			System.out.println("Pass url check on second page");
		}else {
			System.out.println("Fail url check on second page");
		}
		
		
		
		
		// 7). Go back to the main window and click on Build your Lexus.
		driver.switchTo().window(parentWindow);
		driver.findElement(By.xpath("//a[@href=\"/build-your-lexus/#!/\"]")).click();
		
		
		
		
		// 8). Enter “22182” for zipcode and click on Enter on pop-up window.
		driver.findElement(By.xpath("//*[@class='zip-enter byl-js-focus']")).sendKeys("22182");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		
		
		// 9). Click on model GS.
//     	driver.findElement(By.xpath("//img[@src='/byl2014/pub-share/images/series/gs.png']")).click();
     	WebElement k = driver.findElement(By.xpath("//img[@src='/byl2014/pub-share/images/series/gs.png']"));
		JavascriptExecutor js1 = (JavascriptExecutor)driver;
		js1.executeScript("arguments[0].click();", k);
     	
     	
     	
     	
     	driver.manage().window().maximize();
		// 10). Choose 2020 GS 350 F Sport AWD. Before clicking, get the price of the vehicle and save it into an int variable.
		String price = driver.findElement(By.xpath("//*[contains(text(),'54,505')]")).getText().replace(",", "");
		String newprice = price.substring(1, price.length()-1);
		
		int priceInt = Integer.parseInt(newprice);
//		System.out.println(priceInt);
//		driver.findElement(By.xpath("//*[contains(text(),'54,505')]")).click();
		WebDriverWait explictWait2 = new WebDriverWait(driver, 5);
		
		explictWait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[@class='list-title'])[4]"))).click();
		
		// 11). On the next page, click on the price menu on top and retrieve and store the base price, 
		//dp&h fee and msrp into separate int variables. Verify that the base price is the same as the price shown at Step 10.  
		//Verify that msrp equals to base price + dp&h fee;
		
		driver.findElement(By.id("total-price")).click();
		String basePrice = driver.findElement(By.xpath("(//span[@class='title-price-small'])[1]")).getText().replace(",", "");
		int basePriceInt = Integer.parseInt(basePrice.substring(1));

		if(basePriceInt == priceInt) {
		System.out.println("Base Price equals to the Original Price at Step 10");
	}else {
		System.out.println("Base Price NOT equal to the Original Price at Step 10");
	}	
		
		String dphFee = driver.findElement(By.xpath("(//span[@class='title-price-small'])[4]")).getText().replace(",", "");
		int dphFeeInt = Integer.parseInt(dphFee.substring(1));
        
        String msrpPrice1 = driver.findElement(By.xpath("//span[@id='total-amount']")).getText().replace(",", "");
		int msrpPriceInt1 = Integer.parseInt(msrpPrice1);

		if(msrpPriceInt1==(basePriceInt + dphFeeInt)) {
			System.out.println("MSRP Price equals Base Price plus DPH Fee");
		}else {
			System.out.println("MSRP Price NOT equal Base Price plus DPH Fee");
		}
        
        
		// 12). Close the menu and click on Ultrasonic Blue Mica color.
		    driver.findElement(By.id("total-price")).click();
			driver.findElement(By.xpath("//img[@data-code='08X1']")).click();
		
		
		// 13). Click on the price menu on top again and retrieve the price for color and store into int variable. 
		// Retrieve msrp one more time and verify that msrp  now equals to base price + dp&h fee + color
		driver.findElement(By.id("total-price")).click();
		
		String colorPrice = driver.findElement(By.xpath("(//span[@class=\"title-price-small\"])[2]")).getText().replace(",", "");
		String newColorPrice = colorPrice.substring(1);
		int colorPriceInt = Integer.parseInt(newColorPrice);
//		System.out.println(colorPriceInt);
		
		String msrpPrice2 = driver.findElement(By.xpath("//span[@id='total-amount']")).getText().replace(",", "");
		int msrpPriceInt2 = Integer.parseInt(msrpPrice2);
//		System.out.println(msrpPriceInt2);
		
		int expectedMSRP = colorPriceInt + basePriceInt + dphFeeInt;
		
		if(expectedMSRP == msrpPriceInt2) {
			System.out.println("MSRP Price now equals Base Price plus DPH Fee plus color fee");
		}else {
			System.out.println("MSRP Price now NOT equal Base Price plus DPH Fee plus color fee");
		}
		
		// 14). Close the price menu and click on Next:Interior button
		driver.findElement(By.id("total-price")).click();
		driver.findElement(By.xpath("//a[@class='byl-js-goToICol omniture']")).click();
		
		
		
		
		
		// 15). Choose “Rioja Red leather with Naguri Aluminum trim” from the options .Click on Next:packages 
		driver.findElement(By.xpath("//img[@data-code=\"LB36\"]")).click();
		driver.findElement(By.xpath("//a[@class='byl-js-goToPackages omniture']")).click();
		
		
		
		
		// 16). In the next menu, click on add button for Mark Levinson sound system.
		driver.findElement(By.xpath("(//a[@class='byl-js-opt-select addRemove'])[1]")).click();
		
		
		// 17). Click on price menu again and retrieve and store the price for Sound system into int variable. 
		// Retrieve msrp once again and verify that msrp now equals to to base price + dp&h fee + color+sound system.
		driver.findElement(By.id("total-price")).click();
		String soundSystemPrice = driver.findElement(By.xpath("(//span[@class='title-price-small'])[11]")).getText().replace(",", "");
		
		int soundSystemPr = Integer.parseInt(soundSystemPrice.substring(1));
//		System.out.println(soundSystemPr);
		String msrpPrice3 = driver.findElement(By.xpath("//span[@id=\"total-amount\"]")).getText().replace(",", "");
//		String newMSRPPrice = msrpPrice.substring(1);
		int msrpPriceInt3 = Integer.parseInt(msrpPrice3);
//		System.out.println(msrpPriceInt3);
		
		
		if(msrpPriceInt3==(basePriceInt + dphFeeInt + colorPriceInt + soundSystemPr)) {
			System.out.println("MSRP Price equals Base Price plus DPH Fee plus Color price plus Sound system price");
		}else {
			System.out.println("MSRP Price NOT equal Base Price plus DPH Fee plus Color price plus Sound system price");
		}
        
		
		
		// 18). Click on Next:Accessories , on the next menu don’t add anything and click on Next:summary
		driver.findElement(By.xpath("//a[@class='byl-js-goToOptions omniture']")).click();
		driver.findElement(By.xpath("//a[@class=\"display-summary omniture\"]")).click();
		
		
		
		
		// 19). On the next page, retrieve msrp and verify that it is equal to the final msrp from step 17. Then click on Send to Dealer.
		String msrpPrice4 = driver.findElement(By.xpath("//h2[@class='title-price']")).getText().replace(",", "");

		String newMSRPPrice = msrpPrice4.substring(1, msrpPrice4.length()-1);
		System.out.println(newMSRPPrice);
		int msrpPriceInt4 = Integer.parseInt(newMSRPPrice);
//		System.out.println(msrpPriceInt4); //h2[@class='title-price']
		
		if(msrpPriceInt3==msrpPriceInt4) {
			System.out.println("Last MSRP Price equals to msrp Price from Step 17");
		}else {
			System.out.println("Last MSRP Price NOT equal to msrp Price from Step 17");
		}
		
		driver.findElement(By.xpath("//a[@href=\"purchase-inquiry.html\"]")).click();
		
		
		
		
		
		// 20). Next, first verify that the page contains “Send us Your Dream Car” text. Then
		// enter the below information to the form fields, choose Pohanka as preferred dealer and click on submit.
		String parentWindow2 = driver.getWindowHandle();
		   Set<String> windowHandles2 = driver.getWindowHandles();
		for(String handle: windowHandles2) {
			if(!handle.equals(parentWindow2)) {
				driver.switchTo().window(handle);
			}
		}         
		String text = driver.findElement(By.className("title-intro-overlay")).getText();
		String expectedText = "SEND US YOUR DREAM CAR";
		if(text.contains(expectedText)) {
			System.out.println("Pass Text check");
		}else {
			System.out.println("Fail Text check");
		}
		
		
		driver.findElement(By.id("first-name")).sendKeys("John");
		driver.findElement(By.id("last-name")).sendKeys("Doe");
		driver.findElement(By.id("email")).sendKeys("anymail@gmail.com");
		driver.findElement(By.id("phone")).sendKeys("3127250272");
		

		WebElement radio = driver.findElement(By.xpath("//label[@for='64504']"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", radio);
		
		driver.findElement(By.xpath("(//button[@type='submit'][@class='btn-rev'])[1]")).click();
		
		
		
		//21). In the last page, verify that the page contains “We'll Be In Touch Shortly” text.
     String confirmationText = driver.findElement(By.xpath("//div[@class='list-title-sub']")).getText();
     String expectedConfirmationText = "WE'LL BE IN TOUCH SHORTLY";
     if(confirmationText.contains(expectedConfirmationText)) {
			System.out.println("Pass Confirmation Text check");
		}else {
			System.out.println("Fail Confirmation Text check");
		}
		


		
	}

}
