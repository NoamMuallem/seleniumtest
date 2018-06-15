import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChooseBusiness {
    private static WebDriver driver;
    private static String url;

    //constractor
    public ChooseBusiness(WebDriver driver) {
        this.driver = driver;
    }


    public static void ChooseBusinessByIndex(int businessIndex, ExtentTest test) {
        try {
            WebElement business = driver.findElement(By.xpath(Const.BUSINESS_XPATH_PART1 + Integer.toString(businessIndex) + Const.BUSINESS_XPATH_PART2));
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(business));
            business.click();
        } catch (StaleElementReferenceException e) {
            WebElement business = driver.findElement(By.xpath(Const.BUSINESS_XPATH_PART1 + Integer.toString(businessIndex) + Const.BUSINESS_XPATH_PART2));
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(business));
            business.click();
            test.log(Status.INFO, Const.FAIS_TO_CHOOSE_BUSINESS);
        }
    }

    public static void GetUrlFromWeb() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        url = driver.getCurrentUrl();
    }

    public String GetUrl() {
        return url;
    }
}
