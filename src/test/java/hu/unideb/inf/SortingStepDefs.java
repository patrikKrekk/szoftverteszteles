package hu.unideb.inf;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingStepDefs extends AbstractStepDefs {

    @When("the user sorts products by {string}")
    public void theUserSortsProductsBy(String sortingOption) {
        homePage.sortBy(sortingOption);
    }

    @Then("the products should be sorted by {string}")
    public void theProductsShouldBeSortedBy(String sortingOption) {
        List<String> productNames = homePage.getProductNames();
        List<String> sortedProductNames = new ArrayList<>(productNames);

        if (sortingOption.equals("Name (A to Z)")) {
            Collections.sort(sortedProductNames);
        } else if (sortingOption.equals("Name (Z to A)")) {
            Collections.sort(sortedProductNames, Collections.reverseOrder());
        } else if (sortingOption.equals("Price (low to high)")) {
            sortedProductNames.sort(Comparator.comparingDouble(this::extractPrice));
        } else if (sortingOption.equals("Price (high to low)")) {
            sortedProductNames.sort(Comparator.comparingDouble(this::extractPrice).reversed());
        }

        assertEquals(sortedProductNames, productNames);
    }

    private double extractPrice(String productName) {
        WebDriver driver = getDriver();
        String xpath = String.format("//div[contains(text(),'%s')]/ancestor::div[@class='inventory_item']//div[@class='inventory_item_price']", productName);
        String priceText = driver.findElement(By.xpath(xpath)).getText();
        return Double.parseDouble(priceText.replace("$", ""));
    }
}
