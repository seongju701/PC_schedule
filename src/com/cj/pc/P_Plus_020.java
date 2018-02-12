package com.cj.pc;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cj.pack.Duplicate_Plus;
import com.cj.util.SmartProperties;


/**
 ** 
 ** @author 조성주
 **  Date : 2207-11-08
 ** Subject : CJ Mall 운영 
 ** Name : PC 편성표 자동화 020
 ** Scenario : CJmall > TV 쇼핑 > 편성표 > 상품선택 > 바로구매 > 로그인 > 주문서 
 ** Assertion : 주문서 Text 체크
 ** +1day 20
 **
 **/

public class P_Plus_020 {
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
	public void p_schedule_020() throws Exception {
		
		// CJ 오쇼핑 메인 페이지 호출
		driver.get(P_URL);
		
		String Single = ".//*[@id='scheduleItem']/ul[20]/li/a[1]"; //단일상품
		String dobulePro =".//*[@id='scheduleItem']/ul[20]/li[1]/a[1]"; //복수상품
		String singleCon= ".//*[@id='scheduleItem']/ul[20]/li/div/div/span"; // 상담상품
		String dobuleCon= ".//*[@id='scheduleItem']/ul[20]/li[1]/div/div/span"; // 상담상품
		Duplicate_Plus duplicate = new Duplicate_Plus(driver);
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
