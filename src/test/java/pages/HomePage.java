package pages;

import io.cucumber.datatable.DataTable;
import io.cucumber.messages.internal.com.google.gson.internal.bind.util.ISO8601Utils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static sun.security.util.KnownOIDs.EC;
import static utils.CommonUtils.*;
import static utils.DriversUtils.*;


public class HomePage {

    public WebDriver driver;

    public WebDriverWait wait;


    {

    }

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }


    @FindBy(tagName = "h2")
    private WebElement roomCategoryIdentifier;

    @FindBy(xpath = "//button[contains(@class,'openBooking')]")
    private WebElement bookButton;

    @FindBy(xpath = "//input[@name='firstname']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@name='lastname']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@name='phone']")
    private WebElement phoneInput;

    @FindBy(xpath = "//button[text()='Today']")
    private WebElement calenderToday;

    @FindBy(xpath = "//div[@tabindex='0']")
    private WebElement blueSig;

    @FindBy(xpath = "//button[text()='Cancel']")
    private WebElement cancelButton;

    @FindBy(xpath = "//button[text()='Next']")
    private WebElement nextButton;

    @FindBy(xpath = "//button[text()='Book']")
    private WebElement completeBooking;


    public void navigateToHomePage() {
        getDriver().get("https://automationintesting.online/#/");

    }

    public HomePage() {
        PageFactory.initElements(getDriver(), this);
    }

    public void goToRoomsCategory() {
        try {
            scrollToElement(roomCategoryIdentifier);
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("Error in the rooms category method");
        }
    }

    public void assertBookButtonDisplayed() {
        Assert.assertEquals(true, bookButton.isDisplayed());
    }


    public void pressBookButton() {
        bookButton.click();
    }

    public boolean isCalenderTodayDisplayed() {

        return calenderToday.isDisplayed();
    }

    private void fillInput(WebElement inputElement, String value) {
        inputElement.clear();
        inputElement.sendKeys(value);
    }

    public void fillBookingInformation(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : rows) {
            String fieldName = row.get("Field");
            String value = row.get("Value");

            if ("First Name".equals(fieldName)) {
                fillInput(firstNameInput, value);
            } else if ("last name".equalsIgnoreCase(fieldName)) {
                fillInput(lastNameInput, value);
            } else if ("email".equalsIgnoreCase(fieldName)) {
                fillInput(emailInput, value);
            } else if ("phone".equalsIgnoreCase(fieldName)) {
                fillInput(phoneInput, value);
            } else {

                System.out.println("Unknown field: " + fieldName);
            }
        }
    }


    String startDateDay = "24";
    String endDateDay = "26";

    private WebElement getStartDate() {
        return getDriver().findElement(By.className("rbc-month-view")).findElement(By.xpath("//*[text()='" + startDateDay + "']/parent::*"));

    }

    private WebElement getEndDate() {
        return getDriver().findElement(By.className("rbc-month-view")).findElement(By.xpath("//*[text()='" + endDateDay + "']/parent::*"));

    }

    public void pickDate() {
        Actions action = new Actions(getDriver());

        try {
            ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,250)");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        action.click(nextButton).perform();
        action.pause(500).perform();

        action.click(getStartDate());
        action.clickAndHold();
        action.moveToElement(getEndDate());
        action.release();

        action.perform();

    }

    public void checkBlueSigIsDisplayed() {
        try {
            Assert.assertTrue("BlueSig element should be displayed", blueSig.isDisplayed());
        } catch (TimeoutException e) {

            System.out.println("BlueSig element is not displayed within the expected time.");
            e.printStackTrace();
        }
    }

    public void iClickOnTheButton() {
        completeBooking.click();
    }

    @FindBy(xpath = "//p[@xpath]")
    private WebElement toConfirmDate;

    public boolean calculateDateFrom() {
        String dateFromScreen = toConfirmDate.getText();
        String[] dates = dateFromScreen.split(" - ");

        String validationStartDate = dates[0];
        String validationEndDate = dates[1];


        return false;
    }
}


