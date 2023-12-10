package features.step_definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BasePage;
import pages.HomePage;

public class BookingSteps extends BasePage {

    HomePage homePage = new HomePage();
    @Given("I am on the home page")
    public void i_am_on_the_home_page() {
        homePage.navigateToHomePage();

    }

    @When("I browse through the page")
    public void i_browse_through_the_page() {
        homePage.goToRoomsCategory();
    }
    @Then("I have the option to book a room")
    public void i_have_the_option_to_book_a_room() {
       homePage.assertBookButtonDisplayed();
    }



    @Given ("I am on the home page and button to book a room is visible" )
    public void i_am_on_the_home_page_and_book_button_is_visible(){
        homePage.assertBookButtonDisplayed();
    }
    @When("I click the book button")
    public void i_click_the_book_button() {

        homePage.pressBookButton();
    }

    @And("the calendar today is displayed")
    public void the_calendar_today_is_displayed() {

        Assert.assertTrue(homePage.isCalenderTodayDisplayed());
    }


    @And("I fill in the information for booking with:")
    public void i_fill_in_the_information_for_booking_with(DataTable dataTable) {
        homePage.fillBookingInformation(dataTable);
    }


    @And("I select a stay period of two nights")
    public void i_select_a_stay_period_of_two_nights()  {
        homePage.pickDate();
    }

    @And ("I see chosen days")
    public void i_see_chosen_days(){
        homePage.checkBlueSigIsDisplayed();
    }

    @And("I click on the Book button")
    public void iClickOnTheButton() {
        homePage.iClickOnTheButton();

    }

    @Then("I should validate booking dates")
    public void i_should_validate_booking_dates() {
        homePage.calculateDateFrom();
    }


}
