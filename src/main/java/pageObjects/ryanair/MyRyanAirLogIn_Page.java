package pageObjects.ryanair;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage;
import utils.FileReaderManager;

public class MyRyanAirLogIn_Page extends BasePage {

    public MyRyanAirLogIn_Page() {
        super();
        PageFactory.initElements(driver, this);
    }

    private final String USER_NAME = FileReaderManager.getInstance().getConfigReader().getUserName();
    private final String USER_PASSWORD = FileReaderManager.getInstance().getConfigReader().getPassword();

    private @FindBy(id = "cookie-popup")
    WebElement cookiePopUp;

    private @FindBy(xpath = "//div[@class='cookie-popup__close']")
    WebElement cookiePopUpCloseButton;

    private @FindBy(id = "myryanair-auth-login")
    WebElement logInButton;

    private @FindBy(xpath = "//div[@class='modal-form-container']")
    WebElement modalLogIn;

    private @FindBy(name = "emailAddress")
    WebElement emailAddress;

    private @FindBy(xpath = "//div[@class='form-field password']//input[@name='password']")
    WebElement password;

    private @FindBy(xpath = "//button[@class='core-btn-primary']")
    WebElement continueLogInButton;

    public void logInMyRyanAirAccout() throws Exception {
        //Close cookie pop up if available
        closeCookiePopUp();

        //Click on Log-In button
        logInButton.click();

        //Enter email address and password
        sendKeysToWebElement(emailAddress, USER_NAME);
        sendKeysToWebElement(password, USER_PASSWORD);
        continueLogInButton.click();
    }

    private void closeCookiePopUp() {
        if (cookiePopUp.isDisplayed()) {
            cookiePopUpCloseButton.click();
        }
    }

}
