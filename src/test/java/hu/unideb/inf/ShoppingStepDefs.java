package hu.unideb.inf;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShoppingStepDefs extends AbstractStepDefs {

    @Given("the {string} is added to the cart")
    public void somethingIsAddedToTheCart(String item) {
        homePage.addItemToCart(item);
    }

    @Then("the price should read {string}")
    public void thePriceShouldRead(String total) {
        assertEquals(total, homePage.getTotal());
    }

    @When("the user proceeds to checkout and finishes the purchase")
    public void theUserProceedsToCheckoutAndFinishesThePurchase() {
        homePage.clickButton("Cart");
        homePage.clickButton("Checkout");
        homePage.fillOutField("First Name", "testname_first");
        homePage.fillOutField("Last Name", "testname_last");
        homePage.fillOutField("Zip Code", "1111");
        homePage.clickButton("Continue");
        homePage.finishCheckout();
    }

    @Then("the confirmation message {string} should be displayed")
    public void theConfirmationMessageShouldBeDisplayed(String message) {
        assertEquals(message, homePage.getConfirmationMessage());
    }

    @When("the user goes back to products after finishing the purchase")
    public void theUserGoesBackToProductsAfterFinishingThePurchase() {
        homePage.backToProducts();
    }

    @Then("the products page should be displayed")
    public void theProductsPageShouldBeDisplayed() {
        assertTrue(homePage.isProductsPageDisplayed());
    }
}