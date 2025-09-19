package stepDefinitions;

import TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import rahulshettyacademy.pageobjects.*;

import java.io.IOException;
import java.util.List;

public class stepDefinitionImpl extends BaseTest {

public LandingPage landingPage;
public ProductCatalogue productCatalogue;
public ConfirmationPage confirmationPage;
    @Given("I landed on ECommerse Page")
    public void I_landed_on_ECommerse_Page() throws IOException {
        landingPage = launchApplication();
    }
    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_username_and_password(String username, String password)
    {
        productCatalogue = landingPage.loginApplication(username,password);
    }
    @When("^When I add product (.+) to Cart$")
    public void I_add_product_to_cart(String productName)
    {
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
    }
    @When("^Checkout (.+) and submit the order$")
    public void checkout_submit_order(String productName)
    {
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.verifyProductsDisplay(productName);
        Assert.assertTrue(match);

        CheckOutPage checkOutPage = cartPage.goToCheckout();
        checkOutPage.selectCountry("India");
        confirmationPage = checkOutPage.submitOrder();
    }
    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmationPage(String string)
    {
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
        driver.close();
    }

    @Then("{string} message is displayed")
    public void messageIsDisplayed(String string)
    {
        Assert.assertEquals(string, "Incorrect email or password.");
        driver.close();
    }
}
