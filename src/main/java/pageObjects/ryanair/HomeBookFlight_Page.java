package pageObjects.ryanair;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObjects.BasePage;

import java.util.List;

public class HomeBookFlight_Page extends BasePage {

    public HomeBookFlight_Page() {
        super();
        PageFactory.initElements(driver, this);
    }

    private static final String COUNTRY_PANE_XPATH = "//div[@class='pane left']/div[starts-with(@class,'core-list-small')]/div";
    private static final String COUNTRY_AIRPORT_XPATH = "//div[@class='pane right']/div[starts-with(@class, 'core-list-small')]/div";
    private static final String PAX_INPUT_BUTTON_XPATH = "//div[@class='details-controls']//button[@class='core-btn inc core-btn-wrap']";
    private static final String ONE_WAY_FLIGHT_XPATH = "//div[@class='row-flight-search-type']/div[@class='flight-search-type-option one-way']";

    private @FindBy(xpath = ONE_WAY_FLIGHT_XPATH)
    WebElement oneWayFlightButton;

    private @FindBy(xpath = "//div[@class='col-departure-airport']//input[@role='textbox']")
    WebElement departureButton;

    private @FindBy(xpath = "//div[@class='col-destination-airport']//input[@role='textbox']")
    WebElement destinationButton;

    private @FindBy(xpath = "//div[@class='col-flight-search-right']//button[@class='core-btn-primary core-btn-block core-btn-big']")
    WebElement continueButton;

    private @FindBy(xpath = COUNTRY_PANE_XPATH)
    WebElement countryPane;

    private @FindBy(xpath = "//div[@class='pane right']/div[@class='core-list-small']/div")
    WebElement airportPane;

    private @FindBy(xpath = "//div[@class='focused']/div[@class='disabled-wrap date-input']")
    WebElement dateButton;

    private @FindBy(xpath = "//div[@class='col-passengers']")
    WebElement passengerButton;

    private @FindBy(xpath = "//*[@id='row-dates-pax']/div[2]/div[2]/div[2]/div/div[1]")
    WebElement numberOfAdultsFromWeb;

    private @FindBy(xpath = "//div[@value='paxInput.adults']" + PAX_INPUT_BUTTON_XPATH)
    WebElement incrementNumberOfAdultsButton;

    private @FindBy(xpath = "//div[@value='paxInput.teens']" + PAX_INPUT_BUTTON_XPATH)
    WebElement incrementNumberOfTeensButton;

    private @FindBy(xpath = "//div[@value='paxInput.children']" + PAX_INPUT_BUTTON_XPATH)
    WebElement incrementNumberOfChildrensButton;

    private @FindBy(xpath = "//div[@value='paxInput.infants']" + PAX_INPUT_BUTTON_XPATH)
    WebElement incrementNumberOfInfantsButton;

    private @FindBy(xpath = "//div[@class='content-box arrow_box']")
    WebElement airportPaneBox;

    private @FindBy(xpath = "//div[@class='col-flight-search-right']")
    WebElement searchFlightButton;

    public void fulfillHomePageBookingProcess(String departureAirport, String destinationAirport, String flightDate, String numberOfAdults, String numberOfOthers, String passengerType) throws Exception {
        //Click on One-Way button
        actionMoveAndClick(waitForElementToBeRefreshedAndClickable(By.xpath(ONE_WAY_FLIGHT_XPATH)));
        //clickOnElementUsingCustomTimeout(getWebElementObjectBy(By.xpath(ONE_WAY_FLIGHT_XPATH)), driver, 5);

        //Enter departure airport
        selectAirportFromAutoCompleteList(departureAirport, departureButton);

        //Enter destination airport
        selectAirportFromAutoCompleteList(destinationAirport, destinationButton);

        //Enter flight date
        wait.until(ExpectedConditions.elementToBeClickable(dateButton));
        enterFlightDate(flightDate);

        //Select number of passengers
        waitUntilWebElementIsVisibleAndClick(passengerButton);
        clickPassengersIncrementButton(Integer.valueOf(numberOfAdults), Integer.valueOf(numberOfOthers), passengerType);

        //Hit search flights button
        waitUntilWebElementIsVisibleAndClick(searchFlightButton);
    }

    private void clickPassengersIncrementButton(int numberOfAdults, int numberOfOthers, String passengerType) {
        selectNumberOfAdultPassengers(numberOfAdults, incrementNumberOfAdultsButton);
        switch (passengerType) {
            case "child(s)":
                selectNumberOfOtherPassengers(numberOfOthers, incrementNumberOfChildrensButton);
                break;
            case "teen(s)":
                selectNumberOfOtherPassengers(numberOfOthers, incrementNumberOfTeensButton);
                break;
            case "infant(s)":
                selectNumberOfOtherPassengers(numberOfOthers, incrementNumberOfInfantsButton);
                break;
        }
    }

    private void selectNumberOfAdultPassengers(int numberOfPassengers, WebElement incrementNumberOfPassengerButton) {
        scrollToElementByWebElementLocator(incrementNumberOfPassengerButton);
        for (int ix = 1; ix < numberOfPassengers; ix++) {
            waitUntilWebElementIsVisibleAndClick(incrementNumberOfPassengerButton);
        }
    }

    private void selectNumberOfOtherPassengers(int numberOfPassengers, WebElement incrementNumberOfPassengerButton) {
        scrollToElementByWebElementLocator(incrementNumberOfPassengerButton);
        for (int ix = 1; ix <= numberOfPassengers; ix++) {
            waitUntilWebElementIsVisibleAndClick(incrementNumberOfPassengerButton);
        }
    }

    private void selectAirportFromAutoCompleteList(String airportToClick, WebElement airportButton) throws Exception {
        sendKeysToWebElement(airportButton, airportToClick);
        scrollToElementByWebElementLocator(airportPaneBox);
        List<WebElement> airportOptionsList = driver.findElements(By.xpath(COUNTRY_AIRPORT_XPATH));
        clickAndSearchElementFromDropdownList(airportOptionsList, airportToClick);
    }

    private void enterFlightDate(String flightDate) {
        String dateToSelect = setDateFormat(flightDate, "dd/MM/yyyy", "dd-MM-yyyy");
        String newDateToSelectXpath = replaceValue("//ul[@class='days']//li[@data-id='@DATE@']",
                "@DATE@", dateToSelect);

        WebElement dateFlight = driver.findElement(By.xpath(newDateToSelectXpath));
        scrollToElementByWebElementLocator(wait.until(ExpectedConditions.visibilityOf(dateFlight)));
        waitAndclickElementUsingJS(dateFlight);
    }

}
