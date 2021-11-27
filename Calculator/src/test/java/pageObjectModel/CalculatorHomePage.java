package pageObjectModel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CalculatorHomePage {

	WebDriver driver;

	// c
	public CalculatorHomePage(WebDriver rdriver) {
		driver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	// WE
	@FindBy(xpath = "//tbody/tr[2]/td[2]/div[1]/div[1]/span[4]")
	WebElement addSymbol;

	@FindBy(xpath = "//span[contains(text(),'–')]")
	WebElement subtractSymbol;

	@FindBy(xpath = "//span[contains(text(),'×')]")
	WebElement multiplySymbol;

	@FindBy(xpath = "//tbody/tr[2]/td[2]/div[1]/div[4]/span[4]")
	WebElement divideSymbol;

	@FindBy(xpath = "//tbody/tr[2]/td[2]/div[1]/div[4]/span[4]")
	WebElement equalsSymbol;

	@FindBy(xpath = "//tbody/tr[2]/td[2]/div[1]//span")
	List<WebElement> numList;

	@FindBy(xpath = "//div[@id='sciOutPut']")
	WebElement result;

	// M

	public void add() {
		addSymbol.click();
	}

	public void subtract() {
		subtractSymbol.click();
	}

	public void multiply() {
		multiplySymbol.click();
	}

	public void divide() {
		divideSymbol.click();
	}

	public void equals() {
		equalsSymbol.click();
	}

	public String result() {
		return result.getText();
	}

	public void clickNum(String s) {
		for (int i = 0; i < s.length(); i++) {
			String c = s.charAt(i) + "";
//			System.out.println(c);
			if (c.equals("-")) {
				subtract();
				continue;
			}
			for (WebElement e : numList) {
				if (e.getText().equals(c)) {
					e.click();
					break;
				}
			}
		}
	}
}
