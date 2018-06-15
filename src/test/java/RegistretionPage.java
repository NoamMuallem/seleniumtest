import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegistretionPage {
    private static WebDriver driver;
    private static String urlBeforeClick;
    private static String urlAfterClick;

    @FindBy(how = How.XPATH, using = Const.REGISTRETION_BUTTN_XPATH)
    private static WebElement registretionButton;
    @FindBy(how = How.XPATH, using = Const.NOT_REGISER_YET_XPATH)
    private static WebElement notRegisterdYet;
    @FindBy(how = How.ID, using = Const.FIRST_NAME_ID)
    private static WebElement firstName;
    @FindBy(how = How.ID, using = Const.E_MAIL_ID)
    private static WebElement eMail;
    @FindBy(how = How.ID, using = Const.PASSWORD_ID)
    private static WebElement password;
    @FindBy(how = How.ID, using = Const.CONFIRM_PASSWORD)
    private static WebElement confirmPassword;
    @FindBy(how = How.XPATH, using = Const.I_AGREE_XPATH)
    private static WebElement iAgree;
    @FindBy(how = How.XPATH, using = Const.DO_NOT_SEND_MAIL_XPATH)
    private static WebElement doNotSenMails;
    @FindBy(how = How.XPATH, using = Const.LAST_REGISTRETION_BUTTON_XPATH)
    private static WebElement lastregistrationButton;

    //constractor
    public RegistretionPage(WebDriver driver) {
        this.driver = driver;
    }

    public static void Register(ExtentTest test) {
        urlBeforeClick = driver.getCurrentUrl();
        Actions registration = new Actions(driver);
        registration.click(registretionButton).click(notRegisterdYet).sendKeys(firstName, Const.FIRST_NAME).
                sendKeys(eMail, Const.E_MAIL).sendKeys(password, Const.PASSWORD).sendKeys(confirmPassword, Const.PASSWORD)
                .click(iAgree).click(doNotSenMails).click(lastregistrationButton).build().perform();
        urlAfterClick = driver.getCurrentUrl();
        if (!urlAfterClick.equals(urlBeforeClick)) {
            driver.findElement(By.className(Const.CLOSE_REGISTRATION_WINDOW_CLASS_NAME)).click();
            test.info(Const.REGISTRATION_FILED_MSG);
        } else {
            test.log(Status.PASS, Const.FINISH_REGISTRATION_MSG);
        }
    }

    public static String getUrlBeforeClick() {
        return urlBeforeClick;
    }

    public static String getUrlAfterClick() {
        return urlAfterClick;

    }
}
