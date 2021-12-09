package com.voxsmart.suite.page.object.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.voxsmart.suite.assertions.CustomAssertions.areEqual;
import static com.voxsmart.suite.constants.CMCConstants.WEBSITE_URL;

public class CMCHomePage extends BasePage {

    private static final String EXPECTED_FOOTER_COPYRIGHT_TEXT = "© 2021 CoinMarketCap. All rights reserved";

    @FindBy(how = How.CLASS_NAME, using = "sc-1oj4kco-4")
    private WebElement footerCopyRightText;

    public CMCHomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        waitForElementToAppear(footerCopyRightText);

        return areEqual(WEBSITE_URL, driver.getCurrentUrl())
                && areEqual(EXPECTED_FOOTER_COPYRIGHT_TEXT, footerCopyRightText.getText());
    }
}
