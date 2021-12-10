package com.voxsmart.suite.hooks;

import com.voxsmart.suite.cucumber.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static com.voxsmart.suite.constants.CMCConstants.WEBSITE_URL;

public class TestContextHook {

    private final TestContext testContext;

    public TestContextHook(TestContext testContext) {
        this.testContext = testContext;
    }

    /**
     * Can add any setup tasks here such as: db connections, test data etc.
     */
    @Before
    public void BeforeSteps() {
        testContext.navigateToPage(WEBSITE_URL);
    }

    @After
    public void AfterSteps() {
        testContext.getDriver().quit();
    }
}
