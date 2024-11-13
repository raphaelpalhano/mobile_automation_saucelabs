package com.saucelabs.pages;


import com.saucelabs.map.ProductsPanelMap;
import org.openqa.selenium.By;

public class ProductsPanelPage extends ProductsPanelMap {

	public void openFilterSort() {
		filterButton.click();
	}

	public void sortItemsBy(String typeOfFilter) {
		containerFilters.findElement(By.xpath(String.format("//android.widget.TextView[@text=\"%s\"]", typeOfFilter))).click();
	}

	public String getTitleItemProduct(int item, String titleItemProduct) {
		return  containerItemProduct(item, titleItemProduct).getText();
	}

	public void addItemToCart(int item) {
		containerItem(item).findElement(By.xpath(String.format("//android.widget.TextView[@text=\"ADD TO CART\"])[%d]", item))).click();
	}

	public void addProductToCart(String titleProduct) {
		addCartUsingTitle(titleProduct).click();
	}

	public void removeProductToCart(String titleProduct) {
		removeCartUsingTitle(titleProduct).click();
	}


	public String valueCart() {
		waitElement(containerCart, 1);
		return containerCart.findElement(By.className("android.widget.TextView")).getText();
	}


	public void accesCart() {
		containerCart.click();
	}
}