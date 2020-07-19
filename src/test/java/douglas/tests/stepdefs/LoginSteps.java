package douglas.tests.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class LoginSteps extends BaseSteps {

    private static final String FORGOT_PASSWORD = "//*[contains(@data-ui-name,'loginForm.lostPasswordLink')]";
    private static final String FORGOT_PASSWORD_EMAIL = "//*[contains(@data-ui-name,'resetPassword.email')]";
    private static final String sendForgotPasswordEmail = "//*[contains(@data-ui-name, 'submitLostPasswordForm')]";
    private static final String EMAIL_FORGOT_PASSWORD_SUCCESS_MESSAGE = "//*[contains(@data-ui-name,'successfulMessage')]";

    @FindBy (how = How.ID, using = "uc-banner-centered")
    private  WebElement cookieBanner;

    @FindBy (how = How.ID, using = "uc-btn-accept-banner")
    private  WebElement cookieBannerButton;

    @FindBy(how = How.NAME, using = "email")
    private WebElement email;

    @FindBy(how = How.NAME, using = "password")
    private WebElement password;

    @FindBy(how = How.NAME, using = "LoginForm|SubmitChanges")
    private WebElement login;

    @FindBy(how = How.CLASS_NAME, using = "rd__welcome-to-mydouglas")
    private WebElement welcomeNote;

    @FindBy(how = How.NAME, using = "LoginForm|Error")
    private WebElement invalidCredentialsError;

    @FindBy(how = How.NAME, using = "successfulMessage")
    private WebElement forgotPasswordSuccessMessage;

    @After
    public  void closingTheBrowser(){
        chromeDriver.close();
    }

    public LoginSteps() throws IOException {
        openBrowser();
    }

    @Given("^I am on Douglas login page$")
    public void iAmOnDouglasLoginPage() {
        PageFactory.initElements(chromeDriver, this);
        chromeDriver.get(getPropertyValue("homepage"));
        WebDriverWait wait = new WebDriverWait(chromeDriver, 20000);
        wait.until(ExpectedConditions.visibilityOf(cookieBanner));
    }

    @Given("^I accept cookies agreement$")
    public void iAcceptCookies() {
        cookieBannerButton.click();
    }

    @When("^I enter valid username$")
    public void iEnterValidUsername() {
        WebDriverWait wait = new WebDriverWait(chromeDriver, 20);
        wait.until(ExpectedConditions.visibilityOf(email));
        email.sendKeys(getPropertyValue("email"));
    }

    @And("^I enter valid password$")
    public void iEnterValidPassword() {
        password.sendKeys(getPropertyValue("password"));
    }

    @When("^I click on login$")
    public void iClickOnLogin() {
        login.click();
    }

    @Then("^I should see a popup with welcome note on successful login$")
    public void iShouldSeeMyWelcomeNotePopUp() {
        WebDriverWait wait = new WebDriverWait(chromeDriver, 200);
        wait.until(ExpectedConditions.visibilityOf(welcomeNote));
    }

    @When("^I enter invalid username (.*)$")
    public void iEnterInvalidUsername(String username) {
        email.sendKeys(username);
    }

    @When("^I enter invalid password (.*)$")
    public void iEnterInvalidPassword(String invalidPassword) {
        password.sendKeys(invalidPassword);
    }

    @Then("^I should see an error message (.*)$")
    public void iShouldSeeAnErrorMessage(String errorMessage) {
        WebDriverWait wait = new WebDriverWait(chromeDriver, 200);
        wait.until(ExpectedConditions.visibilityOf(invalidCredentialsError));
        assertEquals(invalidCredentialsError.getText(), errorMessage);
    }

    @When("^I click on forgot password$")
    public void iClickOnForgotPassword() {
        WebDriverWait wait = new WebDriverWait(chromeDriver, 200);
        WebElement forgotPasswordLink = chromeDriver.findElement(By.xpath(FORGOT_PASSWORD));
        wait.until(ExpectedConditions.visibilityOf(forgotPasswordLink));
        forgotPasswordLink.click();
    }

    @When("^I enter email in forgot password popup$")
    public void iEnterEmailInForgotPasswordPopup() {
        chromeDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement forgotPasswordEmailInPopup = chromeDriver.findElement(By.xpath(FORGOT_PASSWORD_EMAIL));
        forgotPasswordEmailInPopup.sendKeys(getPropertyValue("email"));
    }

    @When("^I type the recaptcha$")
    public void iTypeRecaptcha() throws InterruptedException {
       Thread.sleep(10000);
    }

    @When("^I click on send forgot password email button$")
    public void iClickOnForgotEmailSendButton() {
        WebElement forgotPasswordLink = chromeDriver.findElement(By.xpath(sendForgotPasswordEmail));
        forgotPasswordLink.click();
    }

    @Then("^I should see a success message with email address$")
    public void iShouldSeeSuccessMessageWithEmail() {
        WebElement forgotPasswordSuccessMessage = chromeDriver.findElement(By.xpath(EMAIL_FORGOT_PASSWORD_SUCCESS_MESSAGE));
        forgotPasswordSuccessMessage.getText().contains(getPropertyValue("email"));
    }
}
