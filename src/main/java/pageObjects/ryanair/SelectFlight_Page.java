package pageObjects.ryanair;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage;

import java.util.List;

public class SelectFlight_Page extends BasePage {

    public SelectFlight_Page() {
        super();
        PageFactory.initElements(driver, this);
    }

    private static String standardFareSelector_css_selector = "#outbound > form > div.flight-list-wrapper > div > flights-table > div > div.flights-table__rows > div > div > flights-table-fares > div > div.flights-table-fares__wrapper.flights-table-fares__wrapper--loaded > div.flights-table-fares__fare.fare-select.standard";
    private static String allDates_xpath = "//div[@class='base-carousel']//div[@class='scroller']//div[@class='date']";
    private static String passengersNameSeatsTopMenu_xpath = "//core-carousel-slide[contains(@class,'sm-carousel__pax')][@INDEX@]//div[@class='sm-carousel__pax-names']";
    private static String allStandardSeats_xpath = "//span[@class='seat-click']";
    private static String standardSeatsForChilds_xpath = "//div[@class='seat-wrapper seat-group-STANDARD']//span[@class='seat-click']";

    private @FindBy(xpath = "//span[@class='starting-point']")
    WebElement startingPoint;

    private @FindBy(xpath = "//span[@class='destination']")
    WebElement destinationPoint;

    private @FindBy(xpath = "//div[@class='ranimate-flights-table flights-table__flight first last']//div[@class='flight-header__min-price hide-mobile']//div[@class='core-btn-primary']")
    WebElement fromFlightButton;

    private @FindBy(xpath = "//div[@class='ranimate-flight-fares']")
    WebElement flightFares;

    private @FindBy(css = "#outbound > form > div.flight-list-wrapper > div > flights-table > div > div.flights-table__rows > div > div > flights-table-fares > div > div.flights-table-fares__wrapper.flights-table-fares__wrapper--loaded > div.flights-table-fares__fare.fare-select.business-plus.middle > div.flights-table-fares__head")
    WebElement buttonFlightFareFlexiPlus;

    private @FindBy(xpath = "//div[@class='flights-table-fares__fare fare-select standard']//span[@class='flights-table-fares__fare-radio']")
    WebElement radioButtonFlightStandardFare;

    private @FindBy(xpath = "//button[@class='core-btn-primary core-btn-block core-btn-medium']")
    WebElement selectedFlightsContinueButton;

    private @FindBy(xpath = "//div[@class='footer-message']")
    WebElement selectRandomSeatsPopUp;

    private @FindBy(xpath = "//div[@class='footer-message']//button/span[contains(text(), 'random')]")
    WebElement selectRandomSeatsButton;

    private @FindBy(xpath = "//div[@class='same-for-all-form']")
    WebElement selectSameOptionBagsForAllPassengerPopUp;

    private @FindBy(xpath = "//button[@class='core-btn-primary same-for-all-form__button']")
    WebElement selectSameOptionBagsForAllPassengerButton;

    private @FindBy(css = "body > div.FR > main > div.body-section > priority-boarding-view > div.container > div > div.priority-boarding-view__pb-standalone > div.priority-boarding-view__footer > button")
    WebElement continueBagsOption;

    private @FindBy(xpath = "//div[@class='trips-basket trips-cnt']//button")
    WebElement checkOutButton;

    private @FindBy(xpath = "//div[@class='popup-msg']")
    WebElement carHirePopUp;

    private @FindBy(xpath = "//button[@class='popup-msg__button popup-msg__button--cancel']")
    WebElement carHirePopUpCancelButton;

    private @FindBy(xpath = "//div[@class='popup-msg']")
    WebElement popUpMessage;

    private @FindBy(xpath = "//div[@class='popup-msg']/div[@class='popup-msg__close']")
    WebElement popUpMessageClose;

    private @FindBy(xpath = "//div[@class='seat-map-prompt-content']//button")
    WebElement mandatorySeatsPopUp;

    private @FindBy(xpath = "//button[@class='core-btn-primary dialog-overlay-footer__ok-button']")
    WebElement reviewSeatsButton;

    private @FindBy(xpath = "//div[@class='confirm-seats-title']")
    WebElement confirmSeatsScreen;

    private @FindBy(xpath = "//div[@class='fasttrack-popup--pt']")
    WebElement securityFastTrackPopUp;

    private @FindBy(xpath = "//a[@class='fasttrack-popup__close core-link-inline']")
    WebElement securityFastTrackPopUpButtonCancel;


    public void selectFlights(String departure, String destination, String flightDate, String numberOfAdults, String numberOfOthers, String passengerType) throws Exception {
        //Verify Im in the 'Select Flights' Page
        verifySelectFlightsPage(departure, destination);

        //Enter flight date
        selectFlightDate(flightDate);

        //Select flight
        waitUntilWebElementIsVisibleAndClick(fromFlightButton);

        //Select standard Fare
        waitUntilWebElementIsVisible(flightFares);
        actionMoveAndClickByLocator(By.cssSelector(standardFareSelector_css_selector));

        //Click on continue button
        actionMoveAndClickByLocator(By.xpath("//button[@class='core-btn-primary core-btn-block core-btn-medium']"));

        //Select flight seats
        selectFlightSeats(Integer.valueOf(numberOfAdults), Integer.valueOf(numberOfOthers), passengerType);

        //Select same bag options for passengers
        actionMoveAndClickByLocator(By.cssSelector("body > div.FR > main > div.body-section > priority-boarding-view > div.container > div > div.priority-boarding-view__pb-standalone > div.priority-boarding-view__journey-list.priority-boarding-view__journey-list--0 > div > priority-cabin-bag-card:nth-child(1) > div"));
        verifyVisibilityOfPopUp(selectSameOptionBagsForAllPassengerPopUp, selectSameOptionBagsForAllPassengerButton);

        waitAndclickElementUsingJS(checkOutButton);

        //Close seats car hire pop up
        actionMoveAndClick(waitForElementToBeRefreshedAndClickable(By.xpath("//div[@class='trips-basket trips-cnt']//button")));
        //clickOnElementUsingCustomTimeout(getWebElementObjectBy(By.xpath("//div[@class='trips-basket trips-cnt']//button")), driver, 5);
        verifyVisibilityOfPopUp(popUpMessage, popUpMessageClose);
    }

    private void selectFlightSeats(int numberOfAdults, int numberOfOthers, String passengerType) throws Exception {
        if (numberOfOthers > 0 && passengerType.equals("child(s)")) {
            waitUntilWebElementIsVisibleAndClick(mandatorySeatsPopUp);
            setRandomSeats(numberOfAdults + numberOfOthers);
            waitAndclickElementUsingJS(reviewSeatsButton);

            if (waitUntilWebElementIsVisible(confirmSeatsScreen)) {
                waitAndclickElementUsingJS(reviewSeatsButton);
                verifyVisibilityOfPopUp(securityFastTrackPopUp, securityFastTrackPopUpButtonCancel);
            }

        } else {
            waitUntilWebElementIsVisibleAndClick(selectRandomSeatsButton);
        }
    }

    private void verifySelectFlightsPage(String departure, String destination) {
        waitUntilWebElementIsVisible(startingPoint);
        waitUntilWebElementIsVisible(destinationPoint);

        Assert.assertEquals(startingPoint.getText(), departure);
        Assert.assertEquals(destinationPoint.getText(), destination);
    }

    private void selectFlightDate(String flightDate) {
        List<WebElement> datesAvailableAsList = driver.findElements(By.xpath(allDates_xpath));
        String flightDateFormatted = setDateFormat(flightDate, "dd/MM/yyyy", "Ddd dd MMM");
        clickAndSearchElementFromDropdownList(datesAvailableAsList, flightDateFormatted);
    }

    private void setRandomSeats(int numberOfPassengers) throws Exception {
        WebElement element;
        int x = 1;
        for (int ix = 0; ix < numberOfPassengers; ix++) {
            String newXpath = replaceValue(passengersNameSeatsTopMenu_xpath, "@INDEX@", String.valueOf(x));
            x++;
            element = driver.findElement(By.xpath(newXpath));

            if (element.getText().contains("Adult")) {
                selectSeatsRandomByAvailability(numberOfPassengers, allStandardSeats_xpath, ix);
            } else if (element.getText().contains("Child")) {
                selectSeatsRandomByAvailability(numberOfPassengers, standardSeatsForChilds_xpath, ix);
            }
        }
    }

    private void selectSeatsRandomByAvailability(int numberOfPassengers, String locator, int ix) throws Exception {
        List<WebElement> availableSeatsAsList = driver.findElements(By.xpath(locator));
        List<WebElement> availableRandomSeatsPicked = getRandomFromList(availableSeatsAsList, numberOfPassengers);
        scrollToElementByWebElementLocator(availableRandomSeatsPicked.get(ix));
        actionMoveByElement(availableRandomSeatsPicked.get(ix));
        waitAndclickElementUsingJS(availableRandomSeatsPicked.get(ix));
    }
}
