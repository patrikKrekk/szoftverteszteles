package hu.unideb.inf;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoutStepDefs extends AbstractStepDefs {

    @When("the user logs out")
    public void theUserLogsOut() {
        homePage.logout();
    }

    @Then("the login page should be displayed")
    public void theLoginPageShouldBeDisplayed() {
        assertTrue(homePage.isLoginPageDisplayed());
    }
}
