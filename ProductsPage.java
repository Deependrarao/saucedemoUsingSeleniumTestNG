package SwagLabsTestSelenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {

    WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    By title = By.cssSelector(".title");
    By addBackpack = By.id("add-to-cart-sauce-labs-backpack");
    By cartBadge = By.className("shopping_cart_badge");
    By cartIcon = By.id("shopping_cart_container");

    public String getTitle() {
        return driver.findElement(title).getText();
    }

    public void addProductToCart() {
        driver.findElement(addBackpack).click();

    }
    public String getCartCount() {
        return driver.findElement(cartBadge).getText();
    }

    public void clickCart() {
        driver.findElement(cartIcon).click();
    }

}
