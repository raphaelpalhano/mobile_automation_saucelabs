package com.saucelabs.pages;

import com.saucelabs.map.CheckoutMap;
import com.saucelabs.util.Utils;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class CheckoutPage extends CheckoutMap {
    public String productNameCheckout() {
        waitElement(containerDescription, 1);
        MobileElement element = containerDescription.findElements(By.className("android.widget.TextView")).get(0);
        return element.getText();
    }

    public void startCheckout() {
        checkoutButton.click();
    }

    public void fillFormCheckout(String fillFormCondition) {
        String nameData = "";
        String lastNameData = "";
        String zipCodeData = "";

        switch (fillFormCondition) {
            case "valid":
                nameData = Utils.getTestProperty("first_name");
                lastNameData = Utils.getTestProperty("last_name");
                zipCodeData = Utils.getTestProperty("zip_code");
                break;
            case "invalid_name":
                lastNameData = Utils.getTestProperty("last_name");
                zipCodeData = Utils.getTestProperty("zip_code");
                break;
            default:
                System.out.println("Invalid Condition");
        }

        name.sendKeys(nameData);
        lastName.sendKeys(lastNameData);
        zipCode.sendKeys(zipCodeData);
        buttonContinue.click();
    }

    public void finishCheckout() {
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"FINISH\").instance(0))").click();
        finishButton.click();

    }


}