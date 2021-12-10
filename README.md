# coinmarketcap-automation-tests

# Running the project
This project is built with Java 17, Cucumber, Maven, Selenium.

A Maven wrapper is included so a maven installation is not required, however you may need to install Java 17 on your machine.

Run the project with ` .\mvnw.cmd test` on Windows or `./mvnw test` on Linux / Mac

# Notes taken during the project
Thought process:

- When deciding how to correctly assert that a user has landed on the homepage of coinmarketcap.com, I found that there was no obvious title to assert against. Instead I used a combination of the url & footer copy right that is unlikely to change and break the test.

- The footer had an awkward alpha-numeric classname. It's likely this is automatically generated (code obfuscation?) and may change with a later release. This makes for a brittle test.

- Testing future dates is an interesting test case such as trying to select tomorrows date at 23:59, or from a timezone ahead of your current one etc. But didn't add this in the short term.

- CSS Selectors were used for some elements, this is not best practice and instead should be using Id's to make finding the element a more robust process

- The code could be cleaner as well however it has been mostly wrapped in descriptive methods explaining what they do for other devs & testers