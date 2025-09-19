package MyPractice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class DragAndDropDemo {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://jqueryui.com/droppable/");

        WebElement MainIframe = driver.findElement(By.cssSelector(".demo-frame"));
        driver.switchTo().frame(MainIframe);

        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));

        Actions a = new Actions(driver);
        a.dragAndDrop(source,target).build().perform();

        String verifyDroppedText = driver.findElement(By.cssSelector("div[id='droppable'] p")).getText();
        Assert.assertEquals(verifyDroppedText, "Dropped!");

        if (verifyDroppedText.equals("Dropped!"))
        {
            System.out.println("Drag & Drop is successfull!");
        }
        else
        {
            System.out.println("Drag & Drop failed!");
        }

        driver.quit();

    }
}
