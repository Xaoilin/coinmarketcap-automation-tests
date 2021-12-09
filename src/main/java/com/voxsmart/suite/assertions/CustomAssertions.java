package com.voxsmart.suite.assertions;

import lombok.experimental.UtilityClass;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@UtilityClass
public class CustomAssertions {

    public static boolean areEqual(String first, String second) {
        assertThat(first, equalTo(second));
        return true;
    }
}
