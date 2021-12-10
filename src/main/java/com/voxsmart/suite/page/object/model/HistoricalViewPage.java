package com.voxsmart.suite.page.object.model;

import java.util.List;

import com.voxsmart.suite.dto.HistoricalDataRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.voxsmart.suite.assertions.CustomAssertions.areEqual;

public class HistoricalViewPage extends BasePage {

    private static final String EXPECTED_HISTORICAL_DATA_TITLE_TEXT = "Historical Data for";

    // TODO: using generated classnames like the below is not best practice
    @FindBy(how = How.CLASS_NAME, using = "dUjHvS")
    private WebElement historicalDataTitle;

    @FindBy(how = How.CSS, using = "button.x0o17e-0.eXPsOy")
    private WebElement dateRangePicker;

    @FindBy(how = How.CSS, using = "table.cmc-table > tbody > tr")
    private List<WebElement> historicalDataRows;

    public HistoricalViewPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        waitForElementToAppear(historicalDataTitle);

        return historicalDataTitle.getText().contains(EXPECTED_HISTORICAL_DATA_TITLE_TEXT);
    }

    public void clickDatePicker() {
        waitForElementToBeClickable(dateRangePicker);

        dateRangePicker.click();
    }

    public void selectPredefinedDate(DATE_PICKER_OPTION predefinedDate) {
        selectPredefinedDateOption(predefinedDate);

        assertSelectedDatePickerTextIsUpdated(predefinedDate);
    }

    private void assertSelectedDatePickerTextIsUpdated(DATE_PICKER_OPTION predefinedDate) {
        WebElement datePickerSelectedText = driver.findElement(By.cssSelector("div.tippy-content > div > div > div.yzncs8-2.keWhlt > p"));

        String expectedTextResult = String.format("Selected: %d days", predefinedDate.getNumberOfDays());

        areEqual(expectedTextResult, datePickerSelectedText.getText().trim());
    }

    private void selectPredefinedDateOption(DATE_PICKER_OPTION predefinedDate) {
        clickDatePicker();

        String cssSelectorForDate = String.format("div.tippy-content > div > div > div.yzncs8-3.jvJtJs > div.yzncs8-4.jaVFYH > ul > li:nth-child(%d)",
                predefinedDate.getIndexInList());

        WebElement predefinedDateButton = driver.findElement(By.cssSelector(cssSelectorForDate));

        waitForElementToBeClickable(predefinedDateButton);
        predefinedDateButton.click();
    }

    public HistoricalDataRow getLatestHistoricalData() {
        List<WebElement> columns = historicalDataRows.get(0).findElements(By.tagName("td"));

        return HistoricalDataRow.builder()
                .date(columns.get(0).getText())
                .open(columns.get(1).getText())
                .high(columns.get(2).getText())
                .low(columns.get(3).getText())
                .close(columns.get(4).getText())
                .volume(columns.get(5).getText())
                .marketCap(columns.get(6).getText())
                .build();
    }

    public enum DATE_PICKER_OPTION {
        LAST_7_DAYS(7, 1),
        LAST_30_DAYS(30, 2),
        LAST_90_DAYS(90, 3),
        LAST_180_DAYS(180, 4),
        LAST_365_DAYS(365, 5);

        private final int numberOfDays;
        private final int indexInList;

        DATE_PICKER_OPTION(int numberOfDays, int indexInList) {
            this.numberOfDays = numberOfDays;
            this.indexInList = indexInList;
        }

        public int getNumberOfDays() {
            return numberOfDays;
        }

        public int getIndexInList() {
            return indexInList;
        }
    }
}
