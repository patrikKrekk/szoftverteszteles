package hu.unideb.inf;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HomePage {

    private static final String PAGE_URL = "https://www.saucedemo.com/";

    private final WebDriver driver;

    @FindBy(css = "#login_button_container > div > form > div.error-message-container.error > h3")
    private WebElement errorMessage;
    @FindBy(css = "#checkout_summary_container > div > div.summary_info > div.summary_total_label")
    private WebElement priceLabel;

    @FindBy(css = "#checkout_info_container > div > form > div.error-message-container.error > h3")
    private WebElement checkoutErrorMessage;

    private static final Map<String, By> textFields = Map.of(
            "Username", By.id("user-name"),
            "Password", By.id("password"),
            "First Name", By.id("first-name"),
            "Last Name", By.id("last-name"),
            "Zip Code", By.id("postal-code")
    );

    private static final Map<String, By> itemButtons = Map.of(
            "Sauce Labs Backpack", By.id("add-to-cart-sauce-labs-backpack"),
            "Sauce Labs Bike Light", By.id("add-to-cart-sauce-labs-bike-light"),
            "Sauce Labs Bolt T-Shirt", By.id("add-to-cart-sauce-labs-bolt-t-shirt"),
            "Sauce Labs Fleece Jacket", By.id("add-to-cart-sauce-labs-fleece-jacket"),
            "Sauce Labs Onesie", By.id("add-to-cart-sauce-labs-onesie"),
            "Test.allTheThings() T-Shirt (Red)", By.id("add-to-cart-test.allthethings()-t-shirt-(red)")
    );

    private static final Map<String, By> navigationButtons = Map.of(
            "Login", By.id("login-button"),
            "Cart", By.className("shopping_cart_link"),
            "Checkout", By.id("checkout"),
            "Continue", By.id("continue"),
            "Logout", By.id("logout_sidebar_link"),
            "Finish", By.id("finish")
    );

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public void closePage() {
        driver.quit();
    }

    public void fillOutField(String field, String text) {
        driver.findElement(textFields.get(field)).sendKeys(text);
    }

    public void clickButton(String button) {
        driver.findElement(navigationButtons.get(button)).click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public void addItemToCart(String item) {
        driver.findElement(itemButtons.get(item)).click();
    }

    public String getTotal() {
        return priceLabel.getText();
    }

    public boolean isLoginPageDisplayed() {
        return driver.findElement(By.id("login-button")).isDisplayed();
    }

    public void logout() {
        driver.findElement(By.id("react-burger-menu-btn")).click(); // Open menu
        driver.findElement(navigationButtons.get("Logout")).click(); // Click logout
    }

    public void finishCheckout() {
        driver.findElement(By.id("finish")).click();
    }

    public String getConfirmationMessage() {
        return driver.findElement(By.className("complete-header")).getText();
    }

    public void backToProducts() {
        driver.findElement(By.id("back-to-products")).click();
    }

    public boolean isProductsPageDisplayed() {
        return driver.findElement(By.className("inventory_list")).isDisplayed();
    }

    public void sortBy(String sortingOption) {
        WebElement sortDropdown = driver.findElement(By.className("product_sort_container"));
        Select dropdown = new Select(sortDropdown);
        dropdown.selectByVisibleText(sortingOption);
    }
    public List<String> getProductNames() {
        List<WebElement> productElements = driver.findElements(By.className("inventory_item_name"));
        return productElements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public String getCheckoutErrorMessage() {
        return checkoutErrorMessage.getText();
    }
}