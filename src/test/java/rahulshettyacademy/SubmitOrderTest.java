package rahulshettyacademy;

import TestComponents.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.pageobjects.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    String productName = "ZARA COAT 3";
    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String,String> input) throws IOException {

        String countryName = "India";
        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("product"));
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.verifyProductsDisplay(input.get("product"));
        Assert.assertTrue(match);

        CheckOutPage checkOutPage = cartPage.goToCheckout();
        checkOutPage.selectCountry(countryName);
        ConfirmationPage confirmationPage = checkOutPage.submitOrder();
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }
    //Method to verify Zara Coat 3 is displaying in the Orders page
    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest()
    {
        ProductCatalogue productCatalogue = landingPage.loginApplication("testframe@getnada.com","Labanya15#");
        OrdersPage ordersPage = productCatalogue.goToOrdersPage();
        Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
    }

    //Extent Reports implement

    @DataProvider
    public Object[][] getData() throws IOException
    {
        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\data\\PurchaseOrder.json");
        return new Object[][] {{data.get(0)},{data.get(1)}};
    }
   /* @DataProvider
    public Object[][] getData()
    {
        return new Object[][] {{"testframe@getnada.com","Labanya15#","ZARA COAT 3"},{"kejyha712@robot-mail.com","Labanya15#","ADIDAS ORIGINAL"}};
    }*/
   //HashMap<String,String> map = new HashMap<>();
//        map.put("email","testframe@getnada.com");
//        map.put("password","Labanya15#");
//        map.put("product","ZARA COAT 3");
//
//        HashMap<String,String> map1 = new HashMap<>();
//        map1.put("email","kejyha712@robot-mail.com");
//        map1.put("password","Labanya15#");
//        map1.put("product","ADIDAS ORIGINAL");
}
