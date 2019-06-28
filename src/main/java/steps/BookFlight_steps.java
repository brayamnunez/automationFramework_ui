package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.ryanair.HomeBookFlight_Page;
import pageObjects.ryanair.MyRyanAirLogIn_Page;
import pageObjects.ryanair.PassengersAndPayment_Page;
import pageObjects.ryanair.SelectFlight_Page;
import test_DataTypes.Passenger;

import java.util.List;

public class BookFlight_steps {

    HomeBookFlight_Page homeBookFlight_page = new HomeBookFlight_Page();
    SelectFlight_Page selectFlight_page = new SelectFlight_Page();
    MyRyanAirLogIn_Page myRyanAirLogIn_page = new MyRyanAirLogIn_Page();
    PassengersAndPayment_Page passengersAndPayment_page = new PassengersAndPayment_Page();

    @Given("^I login into myryanair account$")
    public void iLoginIntoMyryanairAccount() throws Throwable {
        myRyanAirLogIn_page.logInMyRyanAirAccout();
    }

    @Then("^I make a booking reservation from \"([^\"]*)\" to \"([^\"]*)\" on date \"([^\"]*)\" for \"([^\"]*)\" adults and \"([^\"]*)\" other \"([^\"]*)\"$")
    public void iMakeABookingReservationFromToOnDateForAdults(String departureAirport, String destinationAirport, String flightDate, String numberOfAdults, String numberOfOthers, String passengerType) throws Throwable {
        homeBookFlight_page.fulfillHomePageBookingProcess(departureAirport, destinationAirport, flightDate, numberOfAdults, numberOfOthers, passengerType);
        selectFlight_page.selectFlights(departureAirport, destinationAirport, flightDate, numberOfAdults, numberOfOthers, passengerType);
    }

    @And("^I fulfill personal info for \"([^\"]*)\" passengers: \"([^\"]*)\", and country \"([^\"]*)\" and mobile phone: \"([^\"]*)\"$")
    public void iFulfillPersonalInfoForPassengersAndCountryAndMobilePhone(String numberOfPassengers, String passengers, String country, String phone) throws Throwable {
        passengersAndPayment_page.fulfillPassengerDetails(numberOfPassengers, passengers, country, phone);
    }

    @When("^I fulfill payment details with:$")
    public void iFulfillCardDetailsCardExpCardDateAndCVC(List<Passenger> passengerInfo) throws Throwable {
        passengersAndPayment_page.fulfillPaymentInfo(passengerInfo);
    }

    @Then("^I should get payment declined message: \"([^\"]*)\"$")
    public void iShouldGetPaymentDeclinedMessage(String errorMessage) {
        passengersAndPayment_page.verifyPaymentDeclinedMessage(errorMessage);
    }
}
