import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomScreen {
    private static WebDriver driver;
    private static String url;

    @FindBy(how = How.XPATH, using = Const.PRICE_DROPDOWN_XPATH)
    private static WebElement priceDropdown;
    @FindBy(how = How.XPATH, using = Const.LOCATION_DROPDOWN_XPATH)
    private static WebElement locationDropdown;
    @FindBy(how = How.XPATH, using = Const.CATEGORY_DROPDOWN_XPATH)
    private static WebElement categoryDropdown;
    @FindBy(how = How.XPATH, using = Const.SEARCH_BUTTON)
    private static WebElement searchButton;

    //constractor
    public HomScreen(WebDriver driver) {
        this.driver = driver;
    }

    public static void SearchPriceLocationAndCategoryByInndex(int priceIndex, int locationIndex, int categoryIndex, ExtentTest test) {
        //price
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(priceDropdown));
            priceDropdown.click();
            List<WebElement> prices = driver.findElements(By.className(Const.DROPDOEN_OPTION_CLASS_NAME));
            prices.get(priceIndex).click();
        } catch (WebDriverException e) {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(priceDropdown));
            priceDropdown.click();
            List<WebElement> prices = driver.findElements(By.className(Const.DROPDOEN_OPTION_CLASS_NAME));
            prices.get(priceIndex).click();
        }
        //location
        try {
            WebDriverWait wait1 = new WebDriverWait(driver, 10);
            wait1.until(ExpectedConditions.elementToBeClickable(locationDropdown));
            locationDropdown.click();
            List<WebElement> locations = driver.findElements(By.className(Const.DROPDOEN_OPTION_CLASS_NAME));
            locations.get(locationIndex).click();
        } catch (WebDriverException e) {
            WebDriverWait wait1 = new WebDriverWait(driver, 10);
            wait1.until(ExpectedConditions.elementToBeClickable(locationDropdown));
            locationDropdown.click();
            List<WebElement> locations = driver.findElements(By.className(Const.DROPDOEN_OPTION_CLASS_NAME));
            locations.get(locationIndex).click();
        }
        //category
        try {
            WebDriverWait wait2 = new WebDriverWait(driver, 10);
            wait2.until(ExpectedConditions.elementToBeClickable(categoryDropdown));
            categoryDropdown.click();
            List<WebElement> categorys = driver.findElements(By.className(Const.DROPDOEN_OPTION_CLASS_NAME));
            categorys.get(categoryIndex).click();
        } catch (WebDriverException e) {
            WebDriverWait wait2 = new WebDriverWait(driver, 10);
            wait2.until(ExpectedConditions.elementToBeClickable(categoryDropdown));
            categoryDropdown.click();
            List<WebElement> categorys = driver.findElements(By.className(Const.DROPDOEN_OPTION_CLASS_NAME));
            categorys.get(categoryIndex).click();
        }
        //click search
        test.log(Status.PASS, Const.FILL_SEARCH_SUCCSESSFULY);
        searchButton.click();
    }

    public void GetUrlFromWeb() {
        url = driver.getCurrentUrl();
    }

    public String GetUrl() {
        return url;
    }

}
