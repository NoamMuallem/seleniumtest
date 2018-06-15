import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class LastDetailsPage {
    private static WebDriver driver;
    private static String url;

    //constractor
    public LastDetailsPage(WebDriver driver) {
        this.driver = driver;
    }


    public static void GetUrlFromWeb() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        url = driver.getCurrentUrl();
    }

    public static String GetUrl() {
        return url;
    }

    private static WebElement element = null;

    @FindBy(how = How.XPATH, using = Const.FOR_FRIND_XPATH)
    public static WebElement forFrindRadioButton;
    @FindBy(how = How.XPATH, using = Const.RECIVER_NAME_XPATH)
    public static WebElement recieverName;
    @FindBy(how = How.ID, using = Const.SENDER_NAME_ID)
    public static WebElement senderName;
    @FindBy(how = How.ID, using = Const.MAG_ID)
    public static WebElement msgForCard;
    @FindBy(how = How.CLASS_NAME, using = Const.AFTER_PAYMENT_CLASS_NAME)
    public static WebElement afterPaymentCheckbox;
    @FindBy(how = How.CLASS_NAME, using = Const.CHOOSE_E_MAIL_CLASS_NAME)
    public static WebElement chooseEmail;
    @FindBy(how = How.XPATH, using = Const.ENTER_E_MAIL_XPATH)
    public static WebElement enterEmail;
    @FindBy(how = How.CLASS_NAME, using = Const.SAVE_MAIL_CLASS_NAME)
    public static WebElement saveMail;
    @FindBy(how = How.CLASS_NAME, using = Const.SUBMIT_CLASS_NAME)
    public static WebElement submit;

    private static void UploadaPic() {
        element = driver.findElement(By.xpath(Const.UPLOAD_PIC_XPATH));
        element.sendKeys(Const.PIC_ULR);
    }

    private static void ChooseOccasionsByIndex(int index) {
        List<WebElement> chosenSingel = driver.findElements(By.className(Const.SINGEL_CHOSE_LIST_CLASS_NAME));
        chosenSingel.get(6).click();
        List<WebElement> events = driver.findElements(By.className(Const.SUB_EVENTS_CLASS_NAME));
        events.get(index).click();
    }

    public static void FillForm(int eventsIndex) {
        senderName.clear();
        msgForCard.clear();
        UploadaPic();
        ChooseOccasionsByIndex(eventsIndex);
        Actions fillingFormPart1 = new Actions(driver);
        fillingFormPart1.click(forFrindRadioButton).sendKeys(recieverName, Const.RECIVER_NAME).sendKeys(senderName,
                Const.SENDER_NAME).sendKeys(msgForCard, Const.GIFT_TEXT).
                click(afterPaymentCheckbox).click(chooseEmail).build().perform();
        enterEmail.clear();
        Actions fillingFormPart2 = new Actions(driver);
        fillingFormPart2.sendKeys(enterEmail, Const.SENDER_E_MAIL)
                .click(saveMail).click(submit).build().perform();
    }
}
