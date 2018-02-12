package com.cj.pc;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cj.pack.Duplicate;
import com.cj.util.SmartProperties;


/**
 ** 
 ** @author 조성주
 **  Date : 2017-11-08
 ** Subject : CJ Mall 운영 
 ** Name : PC 편성표 자동화 011
 ** Scenario : CJmall > TV 쇼핑 > 편성표 > 상품선택 > 바로구매 > 로그인 > 주문서 
 ** Assertion : 주문서 Text 체크
 ** +1day 01
 **
 **/

public class P_Live_011 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	private String P_URL = null;

	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		SmartProperties sp = SmartProperties.getInstance();
	
		P_URL = sp.getProperty("P_URL");

	}

	@Test
	public void p_schedule_011() throws Exception {
		
		// CJ 오쇼핑 메인 페이지 호출
		driver.get(P_URL);
		Thread.sleep(3000);
		
		String Single = ".//*[@id='scheduleItem']/ul[11]/li/a[1]"; //11번째 단일상품
		String dobulePro =".//*[@id='scheduleItem']/ul[11]/li[1]/a[1]"; //11번째 복수상품
		String singleCon= ".//*[@id='scheduleItem']/ul[11]/li/div/div/span"; // 11번째 상담상품
		String dobuleCon= ".//*[@id='scheduleItem']/ul[11]/li[1]/div/div/span"; // 11번째 상담상품
		Duplicate duplicate = new Duplicate(driver);
		duplicate.schedule(Single,dobulePro,singleCon,dobuleCon);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	public boolean existElement(WebDriver wd, By by, String meaning) {
		WebDriverWait wait = new WebDriverWait(wd, 2);
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(by));

		} catch (TimeoutException e) {

			System.out.println("[" + meaning + "] WebElement does not Exist. time out ");
			return false;
		}
		System.out.println("[" + meaning + "] WebElement Exist.");
		return true;
	}
	
}
