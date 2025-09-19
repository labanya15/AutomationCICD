package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

import java.time.Duration;

public class CheckOutPage extends AbstractComponent {

    WebDriver driver;
    public CheckOutPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
    @FindBy(css = "[placeholder='Select Country']")
    WebElement country;

    @FindBy(css = ".action__submit")
    WebElement submit;

    @FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
    WebElement selectCountry;

    By results = By.cssSelector(".ta-results");

    public void selectCountry(String countryName)
    {
        Actions a = new Actions(driver);
        a.sendKeys(country,countryName).build().perform();
        waitForElementToAppear(results);
        selectCountry.click();
    }
    public ConfirmationPage submitOrder()
    {
        WebElement button = driver.findElement(By.cssSelector(".action__submit"));
        Dimension buttonSize = button.getSize();
        System.out.println(buttonSize);
        int buttonWidth = buttonSize.getWidth();
        int buttonHeight = buttonSize.getHeight();

        int xOffset = buttonWidth/4;
        int yOffset = buttonHeight/3;

        Actions act = new Actions(driver);
        act.moveToElement(button,xOffset,yOffset).click().perform();
        System.out.println("Button is clicked at calculated dimensions!");
        //submit.click();
        return new ConfirmationPage(driver);

    }

}
