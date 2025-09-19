package rahulshettyacademy;

import TestComponents.BaseTest;
import TestComponents.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {

    @Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
    public void LoginErrorValidation()
    {
        String productName = "ZARA COAT 3";
        String countryName = "India";
        landingPage.loginApplication("pojyja11@inboxbear.com","Labanya1#");
        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");

    }
    @Test
    public void ProductErrorValidation() throws IOException {

        String productName = "ZARA COAT 3";
        String countryName = "India";

        ProductCatalogue productCatalogue = landingPage.loginApplication("javaku1190@clowmail.com","Labanya15#");
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.verifyProductsDisplay("ZARA COAT 33");
        Assert.assertFalse(match);


    }
}
