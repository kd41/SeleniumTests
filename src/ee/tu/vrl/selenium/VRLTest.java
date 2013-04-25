package ee.tu.vrl.selenium;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class VRLTest {
  private WebDriver driver;

  @Before
  public void before() throws Exception {
    driver = new FirefoxDriver();
    // driver = new HtmlUnitDriver();
    driver.get("http://localhost:8888/home");
  }

  @Test
  public void testNotFilledRegistrationForm() {
    WebElement webElement = driver.findElement(By.id("reg-submit"));
    webElement.click();

    List<WebElement> elements = driver.findElements(By.xpath("//td[contains(@class, 'hidden')]"));

    Assert.assertTrue("The count of error messages is wrong: " + elements.size(), elements.size() == 0);
  }

  @Test
  public void testPartiallyFilledRegistrationForm() {
    WebElement webElementFirstName = driver.findElement(By.id("reg-firstname"));
    WebElement webElementLastName = driver.findElement(By.id("reg-lastname"));
    webElementFirstName.sendKeys("Eesnimi");
    webElementLastName.sendKeys("Perenimi");

    WebElement webElement = driver.findElement(By.id("reg-submit"));
    webElement.click();

    List<WebElement> elements = driver.findElements(By.xpath("//td[contains(@class, 'hidden')]"));

    Assert.assertTrue("The count of error messages is wrong: " + elements.size(), elements.size() == 2);
  }

  @Test
  public void testFullyFilledRegistrationForm() {
    WebElement webElementFirstName = driver.findElement(By.id("reg-firstname"));
    WebElement webElementLastName = driver.findElement(By.id("reg-lastname"));
    WebElement webElementAge = driver.findElement(By.id("reg-age"));
    WebElement webElementRegion = driver.findElement(By.id("reg-region"));
    WebElement webElementParty = driver.findElement(By.id("reg-party"));
    webElementFirstName.sendKeys("Eesnimi");
    webElementLastName.sendKeys("Perenimi");
    webElementAge.sendKeys("33");
    webElementRegion.sendKeys("Järva maakond");
    webElementParty.sendKeys("gg");

    WebElement webElement = driver.findElement(By.id("reg-submit"));
    webElement.click();

    List<WebElement> elements = driver.findElements(By.xpath("//td[contains(@class, 'hidden')]"));
    Assert.assertTrue("The count of error messages is wrong: " + elements.size(), elements.size() == 5);
  }

  @After
  public void after() {
    driver.close();
    driver = null;
  }

}
