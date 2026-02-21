package SwagLabsTestSelenium.tests;

import SwagLabsTestSelenium.base.BaseTest;
import SwagLabsTestSelenium.config.ConfigReader;
import SwagLabsTestSelenium.pages.CheckoutPage;
import SwagLabsTestSelenium.pages.LoginPage;
import SwagLabsTestSelenium.pages.ProductsPage;
import SwagLabsTestSelenium.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SwagTest extends BaseTest {
    LoginPage login;
    ProductsPage product;
    CartPage cart;
    CheckoutPage checkout;

    @Test(priority = 0)
    public void invalidLoginTest() {

        LoginPage login = new LoginPage(driver);

        String username = ConfigReader.get("invalid_username");
        String password = ConfigReader.get("invalid_password");

        login.enterUsername(username);
        login.enterPassword(password);
        login.clickLogin();

        String expectedError = "Epic sadface: Username and password do not match any user in this service";

        Assert.assertEquals(login.getErrorMessage(), expectedError);
        Assert.assertTrue(login.isLoginButtonDisplayed());
    }

    @Test(priority = 1)
    public void validLoginAndPurchaseTest() {

        login = new LoginPage(driver);
        product = new ProductsPage(driver);
        cart = new CartPage(driver);
        checkout = new CheckoutPage(driver);

        String username = ConfigReader.get("valid_username");
        String password = ConfigReader.get("valid_password");

        login.enterUsername(username);
        login.enterPassword(password);
        login.clickLogin();

        Assert.assertEquals(product.getTitle(), "Products");

        product.addProductToCart();
        Assert.assertEquals(product.getCartCount(), "1");

        product.clickCart();
        cart.clickCheckout();

        String firstName = ConfigReader.get("first_name");
        String lastName = ConfigReader.get("last_name");
        String postalCode = ConfigReader.get("postal_code");

        checkout.enterDetails(firstName, lastName, postalCode);
        checkout.finishOrder();

        Assert.assertEquals(checkout.getConfirmationMessage(), "Thank you for your order!");
    }
}
