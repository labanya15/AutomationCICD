package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;

    @FindBy(css = ".cartSection h3")
    List<WebElement> productTitles;

    @FindBy(css = ".totalRow button")
    WebElement checkoutEle;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean verifyProductsDisplay(String productName)
    {
        Boolean match = productTitles.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
        System.out.println("Cart product titles count: " + productTitles.size());
        productTitles.forEach(p -> System.out.println("Cart contains: " + p.getText()));
        System.out.println("Expected product name: " + productName);
        System.out.println("match value is" +match);
        return match;
    }
    public CheckOutPage goToCheckout()
    {
        checkoutEle.click();
        return new CheckOutPage(driver);
    }

}
