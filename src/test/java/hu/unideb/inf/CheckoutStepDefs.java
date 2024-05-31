package hu.unideb.inf;

import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutStepDefs extends AbstractStepDefs {

    @Then("the checkout error message {string} is shown")
    public void theCheckoutErrorMessageIsShown(String errorMessage) {
        assertEquals(errorMessage, homePage.getCheckoutErrorMessage());
    }
}