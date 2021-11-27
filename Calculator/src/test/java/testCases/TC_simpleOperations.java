package testCases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.Test;

import pageObjectModel.CalculatorHomePage;

public class TC_simpleOperations extends BaseClass {
	@Test
	public void performTest() throws IOException {
		try {
			CalculatorHomePage c = new CalculatorHomePage(driver);

			String s1 = num1;
//			System.out.println(s1);
			String s2 = num2;
//			System.out.println(s2);
			c.clickNum(s1);
			logger.info("Number 1 is entered");
			if(oper.equals("+"))
			{
				c.add();
			}
			else if(oper.equals("-"))
			{
				c.subtract();
			}
			else if(oper.equals("*"))
			{
				c.multiply();
			}
			else if(oper.equals("/"))
			{
				c.divide();
			}
			logger.info("Operation Choosen");
			
			c.clickNum(s2);
			logger.info("Number 2 is entered");
			String res=c.result().trim();
//			System.out.println(res);
			if(result.equals(res))
			{
				Assert.assertTrue(true);
				logger.info("Result matched");
			}
			else
			{
				logger.info("Result Not matched");
				captureScreen(driver, "TC_simpleOperations.Test");
				Assert.assertTrue(false);
			}

		} catch (Exception e) {
			// TODO: handle exception
			captureScreen(driver, "TC_simpleOperations.Test");
			System.out.println(e.getMessage());
			logger.error("Exception was handled");
		}
	}
}
