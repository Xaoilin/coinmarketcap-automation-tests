package com.voxsmart.suite.step.definitions;

import java.util.Arrays;
import java.util.HashMap;

import com.voxsmart.suite.cucumber.TestContext;
import com.voxsmart.suite.dto.HistoricalDataRow;
import com.voxsmart.suite.page.object.model.CMCHomePage;
import com.voxsmart.suite.page.object.model.HistoricalViewPage;
import com.voxsmart.suite.page.object.model.HistoricalViewPage.DATE_PICKER_OPTION;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;

import static com.voxsmart.suite.page.object.model.HistoricalViewPage.DATE_PICKER_OPTION.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HistoricalDataSteps {

    private final TestContext testContext;
    private final CMCHomePage homePage;
    private final HistoricalViewPage historicalViewPage;

    private HashMap<DATE_PICKER_OPTION, HistoricalDataRow> historicalDatePickerData;
    private HistoricalDataRow initialDataEntry;

    public HistoricalDataSteps(TestContext testContext) {
        this.testContext = testContext;
        homePage = new CMCHomePage(testContext.getDriver());
        historicalViewPage = new HistoricalViewPage(testContext.getDriver());

        historicalDatePickerData = new HashMap<>();
    }

    @And("user views a random coins historical data")
    public void user_views_a_random_coins_historical_data() {
        assertTrue(homePage.isLoaded());

        WebElement randomCoin = homePage.getRandomCoin();
        homePage.clickHistoricalData(randomCoin);
    }

    @And("checks every predefined date option")
    public void checks_every_predefined_date_option() {
        assertTrue(historicalViewPage.isLoaded());

        recordDataForCurrentDate();
        recordDataForEveryDatePickerOption();
    }

    @Then("the current dates data is always the same")
    public void the_current_dates_data_is_always_the_same() {
        assertEveryDatePickerResultsInTheSameDataForCurrentDate();
    }

    private void recordDataForEveryDatePickerOption() {
        Arrays.stream(values()).forEach(datePickerOption -> {
            historicalViewPage.selectPredefinedDate(datePickerOption);
            historicalDatePickerData.put(datePickerOption, historicalViewPage.getLatestHistoricalData());
        });
    }

    private void recordDataForCurrentDate() {
        initialDataEntry = historicalViewPage.getLatestHistoricalData();
    }

    private void assertEveryDatePickerResultsInTheSameDataForCurrentDate() {
        historicalDatePickerData.forEach((key, value) -> assertEquals(initialDataEntry, value));
    }
}
