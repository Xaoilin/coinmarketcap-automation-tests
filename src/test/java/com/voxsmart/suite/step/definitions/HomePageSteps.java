package com.voxsmart.suite.step.definitions;

import com.voxsmart.suite.cucumber.TestContext;
import com.voxsmart.suite.page.object.model.CMCHomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static com.voxsmart.suite.constants.CMCConstants.WEBSITE_URL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageSteps {

    private final TestContext testContext;
    private final CMCHomePage homePage;

    public HomePageSteps(TestContext testContext) {
        this.testContext = testContext;
        homePage = new CMCHomePage(testContext.getDriver());
    }

    @Given("user navigates to home page")
    public void user_navigate_to_home_page() {
        testContext.navigateToPage(WEBSITE_URL);
    }

    @Then("home page loads correctly")
    public void home_page_loads_correctly() {
        boolean homePageLoaded = homePage.isLoaded();

        assertTrue(homePageLoaded);
    }

    @And("100 results are displayed")
    public void coins_are_displayed() {
        int displayedCoinsCount = homePage.getDisplayedCoinsCount();

        assertThat(displayedCoinsCount, equalTo(100));
    }
}
