import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static java.util.concurrent.TimeUnit.SECONDS;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class WebTest {
    static WebDriver driver;
    static RegistretionPage registrationPage;
    static HomScreen homScreen;
    static ChooseBusiness chooseBusiness;
    static ChooseGift chooseGift;
    static LastDetailsPage lastDetailsPage;
    static ExtentReports extent;
    static ExtentTest test;
    static ArrayList<String> parameters = new ArrayList();


    @BeforeClass
    public static void initilaize() {
        try {
            getData(parameters);
        } catch (ParserConfigurationException e) {
        } catch (IOException e) {
        }
        DriverInit(parameters.get(1));
        reportsInit();
        PageLoad();
        test.log(Status.INFO, Const.BEFOR_TEST);
    }

    @Test
    public void test01_RegisterAndSearch() {

        driver.get(parameters.get(0));
        registrationPage.Register(test);
        Assert.assertNotEquals(registrationPage.getUrlBeforeClick(), registrationPage.getUrlAfterClick());
    }

    @Test
    public void test02_SearchingForBusiness() {
        homScreen.GetUrlFromWeb();
        homScreen.SearchPriceLocationAndCategoryByInndex(Const.PRICE_INDEX, Const.LOCATION_INDEX, Const.CATEGORY_INDEX, test);
        chooseBusiness.GetUrlFromWeb();
        test.log(Status.PASS, Const.FINISH_BUSINESS_LOCATING);
        Assert.assertNotEquals(homScreen.GetUrl(), chooseBusiness.GetUrl());
    }

    @Test
    public void test03_PurchesPresent() {
        chooseBusiness.ChooseBusinessByIndex(Const.BUISINESS_INDEX, test);
        chooseGift.GetUrlFromWeb();
        chooseGift.ChooseGiftFromOptionsByIndex(Const.GIFT_INDEX, test);
        lastDetailsPage.GetUrlFromWeb();
        Assert.assertNotEquals(chooseGift.GetUrl(), lastDetailsPage.GetUrl());
    }

    @Test
    public void test04_FillForm() {
        lastDetailsPage.FillForm(Const.EVENT_INDEX);
    }


    @AfterClass
    public static void terminate() {
        test.log(Status.INFO, Const.FINISH_TEST);
        driver.quit();
        extent.flush();
    }

    //usefull functons
    public static void reportsInit() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(Const.REPORTS_URL);

        // choose to append each test
        htmlReporter.setAppendExisting(true);
        // attach reporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        // name your test and add description
        test = extent.createTest(Const.TEST_NAME, Const.TEST_DESCRIPTION);
    }

    public static void getData(ArrayList<String> output) throws ParserConfigurationException, IOException {

        File configXmlFile = new File("C:\\Users\\noam\\web project\\config.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;

        dBuilder = dbFactory.newDocumentBuilder();

        Document doc = null;

        assert dBuilder != null;
        try {
            doc = dBuilder.parse(configXmlFile);
        } catch (org.xml.sax.SAXException e) {
            e.printStackTrace();
        }

        if (doc != null) {
            doc.getDocumentElement().normalize();
        }
        assert doc != null;
        output.add(doc.getElementsByTagName("URL").item(0).getTextContent());
        output.add(doc.getElementsByTagName("browser").item(0).getTextContent());
    }

    public static void DriverInit(String browser) {
        if (browser.equals("chrome")) {
            System.setProperty(Const.SET_PROPERTY_PART1_CROME, Const.SET_PROPERTY_PART2_CROME);
            driver = new ChromeDriver();
        } else if (browser.equals("explorer")) {
            System.setProperty(Const.SET_PROPERTY_PART1_EXPLORER, Const.SET_PROPERTY_PART2_EXPLORER);
            driver = new InternetExplorerDriver();
        } else if (browser.equals("firefox")) {
            System.setProperty(Const.SET_PROPERTY_PART1_FIREFOX, Const.SET_PROPERTY_PART2_FIREFOX);
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
    }

    public static void PageLoad() {
        registrationPage = PageFactory.initElements(driver, RegistretionPage.class);
        homScreen = PageFactory.initElements(driver, HomScreen.class);
        chooseBusiness = PageFactory.initElements(driver, ChooseBusiness.class);
        chooseGift = PageFactory.initElements(driver, ChooseGift.class);
        lastDetailsPage = PageFactory.initElements(driver, LastDetailsPage.class);
    }

}
