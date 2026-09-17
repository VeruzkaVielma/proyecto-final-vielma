# OrangeHRM UI Test Automation

An end-to-end UI test automation project for the public [OrangeHRM demo application](https://opensource-demo.orangehrmlive.com/). The project uses Java, Selenium WebDriver, Cucumber, TestNG, and the Page Object Model (POM) design pattern to validate authentication and employee-search workflows in Google Chrome.

> The OrangeHRM demo is an external, shared environment. Its employee data and availability may change at any time; this can affect scenarios that search for a specific employee, such as `John`.

## What the project covers

The feature file defines the following business scenarios:

| Area | Scenario | Expected outcome |
| --- | --- | --- |
| Login | Successful login | The user reaches the Dashboard and its header is `Dashboard`. |
| Login | Failed login | Invalid credentials display the expected error and keep the user on the login page. |
| PIM | Search existing employee | A search for `John` returns one or more employee result cards. |
| PIM | Search non-existing employee | A search for `test-automation` displays a no-records notification. |
| PIM | Reset employee search | Reset restores the default employee-list results. |
| End to end | Login, PIM search, navigation, and logout | The user logs in, searches employees, returns to Dashboard, and logs out. |

All scenarios are written in Gherkin in `src/test/resources/features/full_flow.feature` and use the public demo credentials:

```text
Username: Admin
Password: admin123
```

## Architecture and execution flow

The suite separates page behavior from Cucumber step definitions:

```text
Feature file (Gherkin)
        |
        v
Step definitions
        |
        v
Page Objects (LoginPage, DashboardPage, PIMPage)
        |
        v
BasePage (explicit waits and common Selenium actions)
        |
        v
ChromeDriver / OrangeHRM demo application
```

- **Page Objects** hold the locators and page-level actions. They keep Selenium details out of the Gherkin steps.
- **BasePage** creates a 15-second explicit wait and centralizes clicking, typing, element visibility, and URL checks.
- **Hooks** run before and after every scenario. They configure Chrome through WebDriverManager, maximize the browser, and close it at the end.
- **TestRunner** connects Cucumber to TestNG and generates the HTML report.
- **PIM result validation** waits until loading ends and verifies actual result cards using `.oxd-table-body .oxd-table-card`, rather than only checking the table container.

## Project structure

```text
proyectoFinalVielma/
├── pom.xml
├── README.md
├── src/
│   ├── main/java/com/orangehrmlive/demo/pages/
│   │   ├── BasePage.java
│   │   ├── LoginPage.java
│   │   ├── DashboardPage.java
│   │   └── PIMPage.java
│   └── test/
│       ├── java/com/orangehrmlive/demo/tests/
│       │   ├── Runner/
│       │   │   ├── Hooks.java
│       │   │   └── TestRunner.java
│       │   └── steps/
│       │       ├── LoginSteps.java
│       │       ├── PIMSteps.java
│       │       └── FullFlowSteps.java
│       └── resources/features/
│           └── full_flow.feature
└── target/                         # Generated after a test execution
```

## Technical requirements

Install the following before running the project:

- **JDK 25** — the Maven compiler configuration targets Java 25.
- **Apache Maven 3.9+** — to resolve dependencies and execute the test suite.
- **Google Chrome** — tests run in a visible Chrome window.
- Internet access — required for the OrangeHRM demo and, on the first run, WebDriverManager/dependency downloads.
- Git — only needed to clone or push the repository.

Verify the main tools from a terminal:

```powershell
java -version
mvn -version
git --version
```

## Dependencies

Dependencies are managed in `pom.xml`:

| Dependency | Version | Purpose |
| --- | --- | --- |
| Selenium Java | 4.10.0 | Browser automation API. |
| WebDriverManager | 6.3.3 | Resolves and configures ChromeDriver automatically. |
| Cucumber Java | 7.13.0 | Gherkin step definitions and BDD support. |
| Cucumber TestNG | 7.13.0 | Cucumber execution through TestNG. |
| TestNG | 7.8.0 | Test execution framework. |
| Monte Screen Recorder | 0.7.7.0 | Included project dependency for screen-recording support. |

## Setup

### 1. Clone the repository

Using SSH:

```powershell
git clone git@github.com:VeruzkaVielma/proyecto-final-vielma.git
cd proyecto-final-vielma
```

Or, if SSH has not been configured, use HTTPS:

```powershell
git clone https://github.com/VeruzkaVielma/proyecto-final-vielma.git
cd proyecto-final-vielma
```

### 2. Open it in your IDE

Open the folder containing `pom.xml` as a Maven project. IntelliJ IDEA will normally detect it and download the declared dependencies automatically. If prompted, choose **Load Maven Changes**.

### 3. Confirm the JDK

Configure the project SDK and Maven runner JDK to Java 25. A different Java version may cause compilation failures because the project explicitly sets:

```xml
<maven.compiler.source>25</maven.compiler.source>
<maven.compiler.target>25</maven.compiler.target>
```

## Test report and troubleshooting

After execution, open the generated report in a browser:

```text
target/cucumber-reports.html
```

Common issues:

| Symptom | Likely cause and action |
| --- | --- |
| `Permission denied (publickey)` when cloning or pushing | Register your public SSH key in GitHub, load it with `ssh-add`, or use the HTTPS repository URL. |
| ChromeDriver cannot start | Update Chrome, confirm it is installed, then rerun so WebDriverManager can resolve a compatible driver. |
| PIM search for `John` fails | The public demo data may no longer include a matching employee. Inspect the current PIM records and update the feature data if needed. |
| Maven cannot download dependencies | Check your network, proxy, and local Maven repository permissions. |
| Element-not-found or timeout error | The OrangeHRM UI can load asynchronously. Prefer explicit waits and target result cards rather than only a table container. |
| `StaleElementReferenceException` while reading PIM rows | OrangeHRM may re-render the table while Selenium reads it. Wait for the results to finish loading and retry the row lookup. |
| `Timed out waiting for driver server to stop` | The scenario may have completed successfully, but ChromeDriver could not close in time. Close residual Chrome processes and update Selenium/WebDriverManager if it persists. |

## Adding a new test

1. Add a scenario or example to `src/test/resources/features/full_flow.feature`.
2. Add matching step definitions under `src/test/java/com/orangehrmlive/demo/tests/steps`.
3. Add or extend a Page Object in `src/main/java/com/orangehrmlive/demo/pages` for UI actions and locators.
4. Reuse `BasePage` helpers for waits, clicks, and text entry.
5. Run the relevant tag locally and check `target/cucumber-reports.html`.

## Notes

- The suite is intentionally browser-visible to make the automated flow easy to observe.
- Credentials are public demo credentials. Do not reuse this pattern for production credentials; use environment variables or a secure secrets manager instead.
- Locators belong in Page Objects so UI changes can be maintained in one place.
