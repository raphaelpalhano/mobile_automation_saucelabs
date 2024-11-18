package com.saucelabs.pages;


import com.saucelabs.map.LoginMap;
import com.saucelabs.util.Utils;
import org.openqa.selenium.By;

public class LoginPage extends LoginMap {

	public void fillFormLogin(String userCondition) {
		String dataUser = "";
		String dataPass = "";

		switch (userCondition) {
			case "valid":
				dataUser = Utils.getTestProperty("valid_username");
				dataPass = Utils.getTestProperty("password");
				break;
			case "invalid":
				dataUser = Utils.getTestProperty("invalid_username");
				dataPass = Utils.getTestProperty("invalid_password");
				break;
			case "locked_user":
				dataUser = Utils.getTestProperty("blocked_username");
				dataPass = Utils.getTestProperty("password");
				break;


		}
		waitElement(username, 5);

		username.sendKeys(dataUser);
		password.sendKeys(dataPass);
	}

	public void signin() {
		loginButton.click();
		
	}

}