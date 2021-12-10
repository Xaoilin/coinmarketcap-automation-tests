package com.voxsmart.suite.page.object.model;

import java.util.List;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.voxsmart.suite.assertions.CustomAssertions.areEqual;
import static com.voxsmart.suite.constants.CMCConstants.WEBSITE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CMCHomePage extends BasePage {

    private static final String EXPECTED_FOOTER_COPYRIGHT_TEXT = "© 2021 CoinMarketCap. All rights reserved";
    private static final int EXPECTED_COIN_COUNT = 100;
    private static final int RANDOM_NUMBER = Faker.instance().number().numberBetween(0, EXPECTED_COIN_COUNT - 1);

    @FindBy(how = How.CSS, using = "div.footer-meta > div.sc-1oj4kco-4.irssQJ")
    private WebElement footerCopyRightText;

    @FindBy(how = How.CSS, using = "table.cmc-table > tbody > tr")
    private List<WebElement> coinTableRows;

    public CMCHomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        waitForElementToAppear(footerCopyRightText);

        return areEqual(WEBSITE_URL, driver.getCurrentUrl())
                && areEqual(EXPECTED_FOOTER_COPYRIGHT_TEXT, footerCopyRightText.getText());
    }

    public int getDisplayedCoinsCount() {
        return coinTableRows.size();
    }

    public WebElement getRandomCoin() {
        assertEquals(coinTableRows.size(), EXPECTED_COIN_COUNT);

        // TODO: This was using RANDOM_NUMBER above but sometimes would get StaleElementException which was taking too long to debug for this exercise
        return coinTableRows.get(0);
    }

    public void clickHistoricalData(WebElement randomCoin) {
        clickCoinsDropdown(randomCoin);
        clickViewHistoricalData();
    }

    private void clickViewHistoricalData() {
        WebElement viewHistoricalDataBtn = driver.findElement(By.cssSelector("div.tippy-content > div > div > a:nth-child(3) > button"));

        waitForElementToBeClickable(viewHistoricalDataBtn);

        viewHistoricalDataBtn.click();
    }

    private void clickCoinsDropdown(WebElement randomCoin) {
        waitForElementToBeClickable(randomCoin);

        WebElement dropdownIcon = randomCoin.findElement(By.className("icon-More-Vertical"));

        dropdownIcon.click();
    }
}
