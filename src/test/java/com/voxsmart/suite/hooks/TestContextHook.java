package com.voxsmart.suite.hooks;

import com.voxsmart.suite.cucumber.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

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
    }

    @After
    public void AfterSteps() {
        testContext.getDriver().quit();
    }
}
