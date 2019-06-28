package pageObjects.ryanair;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pageObjects.BasePage;
import test_DataTypes.Passenger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PassengersAndPayment_Page extends BasePage {

    public PassengersAndPayment_Page() {
        super();
        PageFactory.initElements(driver, this);
    }

    private static final String PAX_FORM_ELEMENT_XPATH = "//div[@class='pax-form-element']";
    private static final String PAX_FORM_ELEMENT_INDEX_XPATH = PAX_FORM_ELEMENT_XPATH + "//ng-form[@name='passengerForm@INDEX@']";
    private static final String PAX_FORM_ELEMENT_INDEX_SELECT_XPATH = PAX_FORM_ELEMENT_INDEX_XPATH + "//select";
    private static final String PAX_FORM_ELEMENT_INDEX_FIRST_NAME_XPATH = PAX_FORM_ELEMENT_INDEX_XPATH + "//div[@class='form-field payment-passenger-first-name']//input";
    private static final String PAX_FORM_ELEMENT_INDEX_LAST_NAME_XPATH = PAX_FORM_ELEMENT_INDEX_XPATH + "//div[@class='form-field payment-passenger-last-name']//input";
    private static final String MOBILE_COUNTRY_SELECT_XPATH = "//select[@name='phoneNumberCountry']";
    private static final String CREDIT_CARD_INFO_XPATH = "//payment-method-card[@class='card-method']";
    private static final String CREDIT_CARD_BILLING_INFO_XPATH = "//address-form[@class='billing-address']";
    private static final String PAYMENT_DECLINED_PROMPT_XPATH = "//prompt[@text-title='common.components.payment_forms.error_title']";

    private Map<String, List<String>> passengersMap = new HashMap<>();

    private @FindBy(xpath = "//form[@name='bookingPaymentForm']/div[@class='main-area']")
    WebElement passengerDetailsScreen;

    private @FindBy(xpath = PAX_FORM_ELEMENT_XPATH)
    WebElement passengerFormElements;

    private @FindBy(xpath = PAX_FORM_ELEMENT_INDEX_XPATH + "//select")
    WebElement passengerTitle;

    private @FindBy(xpath = PAX_FORM_ELEMENT_INDEX_XPATH + "//div[@class='form-field payment-passenger-last-name']")
    WebElement passengerLastName;

    private @FindBy(xpath = "//div[@class='core-card available-step after-pax-validation-step']")
    WebElement contactDetailsAndPaymentSection;

    private @FindBy(xpath = "//input[@name='phoneNumber']")
    WebElement inputPhoneNumber;

    private @FindBy(xpath = MOBILE_COUNTRY_SELECT_XPATH)
    WebElement mobileDropDownPhone;

    private @FindBy(xpath = CREDIT_CARD_INFO_XPATH + "//input[@name='cardNumber']")
    WebElement cardNumber;

    private @FindBy(xpath = CREDIT_CARD_INFO_XPATH + "//select[@name='expiryMonth']")
    WebElement cardNumberExpMonth;

    private @FindBy(xpath = CREDIT_CARD_INFO_XPATH + "//select[@name='expiryYear']")
    WebElement cardNumberExpYear;

    private @FindBy(xpath = CREDIT_CARD_INFO_XPATH + "//input[@name='securityCode']")
    WebElement cardNumberSecurityCode;

    private @FindBy(xpath = CREDIT_CARD_INFO_XPATH + "//input[@name='cardHolderName']")
    WebElement cardNumberHolderName;

    private @FindBy(xpath = CREDIT_CARD_BILLING_INFO_XPATH + "//input[@name='billingAddressAddressLine1']")
    WebElement billingAddress_1;

    private @FindBy(xpath = CREDIT_CARD_BILLING_INFO_XPATH + "//input[@name='billingAddressCity']")
    WebElement billingAddressCity;

    private @FindBy(xpath = CREDIT_CARD_BILLING_INFO_XPATH + "//input[@name='billingAddressPostcode']")
    WebElement billingAddressPostalCode;

    private @FindBy(xpath = "//select[@id='billingAddressCountry']")
    WebElement countryDropdown;

    private @FindBy(xpath = "//select[@name='currency']")
    WebElement currencyDropdown;

    private @FindBy(xpath = "//div[@class='terms']//input")
    WebElement acceptPaymentTermsCheckbox;

    private @FindBy(xpath = "//div[@class='cta']//button[@class='core-btn-primary core-btn-medium']")
    WebElement payNowButton;

    private @FindBy(xpath = PAYMENT_DECLINED_PROMPT_XPATH)
    WebElement paymentDeclinedPrompt;

    private @FindBy(xpath = PAYMENT_DECLINED_PROMPT_XPATH + "//div[@class='info-text']")
    WebElement paymentDeclinedPromptMessage;

    public void fulfillPassengerDetails(String numberOfPassengers, String passengers, String country, String phone) throws Exception {
        waitUntilWebElementIsVisibleUsingByLocator(By.xpath(PAX_FORM_ELEMENT_XPATH));
        passengersMap = setPassengersInfoAsMap(Integer.valueOf(numberOfPassengers), separateListBy(passengers, "\\s*,\\s*"));
        fulfillPassengersInfo(Integer.valueOf(numberOfPassengers), passengersMap);
        fulfillContactInfo(country, phone);
    }

    public void fulfillPaymentInfo(List<Passenger> passengerInfo) throws Exception {
        setCreditCardDetails(passengerInfo);
        setBillingInfo(passengerInfo);

        clickJS(acceptPaymentTermsCheckbox);
        actionMoveAndClick(payNowButton);
    }

    public void verifyPaymentDeclinedMessage(String errorMessage) {
        if (waitUntilWebElementIsVisible(paymentDeclinedPrompt)) {
            Assert.assertEquals(paymentDeclinedPromptMessage.getText(), errorMessage);
        }
    }

    private Map<String, List<String>> setPassengersInfoAsMap(int numberOfPassengers, List<
            String> passengersAsList) {
        for (int ix = 0; ix < numberOfPassengers; ix++) {
            passengersMap.put("p" + ix, separateListBy(passengersAsList.get(ix), "\\s"));
        }
        return passengersMap;
    }

    private void setCreditCardDetails(List<Passenger> passengerInfo) throws Exception {
        Select expMonthDropdownToSelect = new Select(cardNumberExpMonth);
        Select expYearDropdownToSelect = new Select(cardNumberExpYear);

        for (Passenger passenger : passengerInfo) {
            scrollToWebElementByWebElementLocatorAndClick(cardNumber);
            sendKeysToWebElement(cardNumber, passenger.getCreditCardNumber());
            clickAndSearchElementFromDropdownList(expMonthDropdownToSelect.getOptions(), passenger.getExpMonth());
            clickAndSearchElementFromDropdownList(expYearDropdownToSelect.getOptions(), passenger.getExpYear());
            sendKeysToWebElement(cardNumberSecurityCode, passenger.getCvc());
            sendKeysToWebElement(cardNumberHolderName, passenger.getNameAndLastName());
            selectCurrency("EUR");
        }
    }

    private void selectCurrency(String elementToSelect) {
        Select currencyDropdownToSelect = new Select(currencyDropdown);
        scrollToElementByWebElementLocator(currencyDropdown);
        for (WebElement optionToSelect : currencyDropdownToSelect.getOptions()) {
            if (optionToSelect.getText().contains(elementToSelect)) {
                System.out.println("Selecting Currency: " + elementToSelect);
                scrollToWebElementByWebElementLocatorAndClick(optionToSelect);
                break;
            }
        }
    }

    private void setBillingInfo(List<Passenger> passengerInfo) throws Exception {
        Select countryDropdownList = new Select(countryDropdown);

        for (Passenger passenger : passengerInfo) {
            scrollToWebElementByWebElementLocatorAndClick(billingAddress_1);
            sendKeysToWebElement(billingAddress_1, passenger.getAddress());
            sendKeysToWebElement(billingAddressCity, passenger.getCity());
            sendKeysToWebElement(billingAddressPostalCode, passenger.getPostalCode());
            clickAndSearchElementFromDropdownList(countryDropdownList.getOptions(), passenger.getCountry());
        }
    }

    private void fulfillContactInfo(String country, String phone) throws Exception {
        scrollToWebElementByWebElementLocatorAndClick(mobileDropDownPhone);

        Select countryDropdown = new Select(driver.findElement(By.xpath(MOBILE_COUNTRY_SELECT_XPATH)));
        clickAndSearchElementFromDropdownList(countryDropdown.getOptions(), country);

        scrollToElementByWebElementLocator(inputPhoneNumber);
        sendKeysToWebElement(inputPhoneNumber, phone);
    }

    private void fulfillPassengersInfo(int numberOfPassengers, Map<String, List<String>> passengersMap) throws
            Exception {
        for (int ix = 0; ix < numberOfPassengers; ix++) {
            fulfillPassengersTitle(ix, passengersMap);
            fulfillPassengersNameAndLastName(ix, passengersMap);
        }
    }

    private void fulfillPassengersTitle(int ix, Map<String, List<String>> passengersMap) throws
            InterruptedException {
        String dynamicXpathSelect = replaceValue(PAX_FORM_ELEMENT_INDEX_SELECT_XPATH, "@INDEX@", String.valueOf(ix));
        scrollToElementByLocator(By.xpath(dynamicXpathSelect));
        waitAndClickElementsUsingByLocator(By.xpath(dynamicXpathSelect));
        clickOnDynamicDropdownListByText(dynamicXpathSelect, passengersMap.get("p" + ix).get(0));
    }

    private void fulfillPassengersNameAndLastName(int ix, Map<String, List<String>> passengersMap) throws
            Exception {
        String dynamicXpathFirstName = replaceValue(PAX_FORM_ELEMENT_INDEX_FIRST_NAME_XPATH, "@INDEX@", String.valueOf(ix));
        String dynamicXpathLastName = replaceValue(PAX_FORM_ELEMENT_INDEX_LAST_NAME_XPATH, "@INDEX@", String.valueOf(ix));

        sendKeysToWebElement(getWebElementObjectBy(By.xpath(dynamicXpathFirstName)), passengersMap.get("p" + ix).get(1));
        sendKeysToWebElement(getWebElementObjectBy(By.xpath(dynamicXpathLastName)), passengersMap.get("p" + ix).get(2));
    }
}
