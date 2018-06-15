import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.NoSuchElementException;

public class ChooseGift {
    private static WebDriver driver;
    private static String url;

    //constractor
    public ChooseGift(WebDriver driver) {
        this.driver = driver;
    }


    public static void ChooseGiftFromOptionsByIndex(int giftIndex, ExtentTest test) {
        List<WebElement> gifts = driver.findElements(By.linkText(Const.GIFTS_LINK_TEXT));
        if (TrueIfUrlNotChanged(gifts, giftIndex, test)) {
            try {
                WebElement amount = driver.findElement(By.className(Const.AMOUNT_FILED_CLASS_NAME));
                amount.sendKeys(Const.AMOUNT_TO_SEND);
                //if url changed element will not be found and amount = null
                driver.findElement(By.xpath(Const.AFTER_AMOUNT_BUY_BUTTON_XPATH)).click();
                test.log(Status.PASS, Const.ENTERD_AMOUNT);
            } catch (NoSuchElementException e) {
                test.log(Status.FAIL, Const.COULD_NOT_FOUND_BUYING_BUTTON);
            } catch (StaleElementReferenceException e) {
                WebElement amount = driver.findElement(By.className(Const.AMOUNT_FILED_CLASS_NAME));
                amount.sendKeys(Const.AMOUNT_TO_SEND);
                //if url changed element will not be found and amount = null
                driver.findElement(By.xpath(Const.AFTER_AMOUNT_BUY_BUTTON_XPATH)).click();
                test.log(Status.PASS, Const.ENTERD_AMOUNT);
            }

        }
    }

    public static boolean TrueIfUrlNotChanged(List<WebElement> gifts, int giftIndex, ExtentTest test) {
        //cicking + making sure the page have changed
        boolean answer = true;
        //if ther is only price entering gift an out of index exception will be thrown
        try {
            if (giftIndex < gifts.size()) {
                gifts.get(giftIndex).click();
                answer = false;
                test.log(Status.PASS, Const.SUCCESSFULY_FOUND_GIFT);
            } else {
                gifts.get(0).click();
                answer = false;
                test.log(Status.INFO, Const.DEFULT_GIFT_ENTERED);
            }
        } catch (IndexOutOfBoundsException e) {
        } catch (NoSuchElementException e) {
        }
        return answer;
    }

    public static void GetUrlFromWeb() {
        url = driver.getCurrentUrl();
    }

    public static String GetUrl() {
        return url;
    }
}
