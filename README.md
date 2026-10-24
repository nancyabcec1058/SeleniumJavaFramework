# 🌐 Selenium Java Hybrid Framework (TestNG + Cucumber BDD)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-FF6C37?style=for-the-badge)
![Cucumber](https://img.shields.io/badge/Cucumber-23D96C?style=for-the-badge&logo=cucumber&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

A **hybrid UI test automation framework** for a demo e-commerce web application, built with **Selenium WebDriver 4**, **Java**, **TestNG** and **Cucumber**. It combines the **Page Object Model** and **Page Factory** with data-driven tests, parallel execution, automatic retries, screenshots on failure and **Extent Reports**, all controlled through **Maven profiles**.

---

## ✨ Key features

| Area | What's implemented |
|---|---|
| **Page Object Model + Page Factory** | Login, Product Catalogue, Cart, Checkout, Confirmation and Orders pages using `@FindBy` and `PageFactory.initElements` |
| **Reusable components** | A shared base class holds explicit waits and header navigation (Cart, My Orders) used by every page |
| **Cross-browser support** | Chrome, Edge and Firefox, chosen in `GlobalData.properties` or at runtime with `-Dbrowser=` |
| **Driver management** | WebDriverManager downloads matching driver binaries automatically |
| **Data-driven testing** | TestNG `@DataProvider` reads test data from JSON (`PurchaseOrder.json`) into `HashMap`s with Jackson |
| **Parallel execution** | TestNG suites run `<test>` blocks in parallel with `ThreadLocal` Extent reporting for thread safety |
| **Retry mechanism** | `RetryAnalyzer` (TestNG `IRetryAnalyzer`) re-runs flaky tests once |
| **Screenshots on failure** | `TestNGListener` captures a screenshot when a test fails and attaches it to the Extent report |
| **Test grouping** | `purchaseOrder` and `ErrorHandling` groups, plus `dependsOnMethods` for order-history checks |
| **BDD layer** | Cucumber feature files with `Background` and `Scenario Outline`, run through TestNG (`AbstractTestNGCucumberTests`) |
| **Maven profiles** | `Regression`, `Purchase`, `ErrorValidation` and `CucumberTest` profiles pick which suite runs |
| **Reporting** | Extent Spark reports for TestNG and Cucumber (via the Extent Cucumber 7 adapter), plus Cucumber HTML |

## 🧰 Tech stack

| Tool | Version | Purpose |
|---|---|---|
| Java | 11+ | Language |
| Selenium WebDriver | 4.44.0 | Browser automation |
| TestNG | 7.11.0 | Test runner, data providers, listeners, parallel runs |
| Cucumber JVM + TestNG | 7.x | BDD scenarios |
| WebDriverManager | 6.3.4 | Driver binary management |
| Jackson Databind | 2.21.3 | JSON test data |
| ExtentReports | 5.1.1 | HTML reporting |
| Maven Surefire | 3.5.6 | Suite execution through profiles |

## 📁 Project structure

```
SeleniumJavaFramework/
├── pom.xml                                  # Dependencies + Maven profiles
├── TestSuite/
│   ├── testng.xml                           # Full regression (parallel) with listener
│   ├── Purchase.xml                         # purchaseOrder group
│   └── ErrorValidationTest.xml              # ErrorHandling group
└── src/
    ├── main/java/
    │   ├── PageObjects/                     # LoginPage, ProductCatalogue, CartPage,
    │   │                                    # CheckoutPage, ConfirmationPage
    │   ├── Utilities/
    │   │   ├── ReusableComponents.java      # Waits + common navigation
    │   │   └── OrderPage.java
    │   └── Resources/
    │       ├── ExtentReport.java            # Extent Spark reporter setup
    │       └── GlobalData.properties        # browser=chrome
    └── test/java/
        ├── TestComponents/
        │   ├── BaseTest.java                # Driver setup, JSON reader, screenshots
        │   ├── TestNGListener.java          # Extent logging + failure screenshots
        │   └── RetryAnalyzer.java           # Retry flaky tests
        ├── Testsite/
        │   ├── SubmitOrder.java             # End-to-end purchase + order history
        │   └── ErrorValidation.java         # Negative login + cart validation
        ├── Cucumber/
        │   ├── SubmitOrder.feature
        │   ├── ErrorValidation.feature
        │   └── TestNGRunner.java
        ├── stepDefinition/PurchasestepDefinition.java
        └── TestData/PurchaseOrder.json
```

## 🧪 Test scenarios

| Test | Type | What it checks |
|---|---|---|
| `submitOrder` | Data-driven (JSON) | Login, add product, verify cart, checkout, confirm "THANKYOU FOR THE ORDER." |
| `checkOrderHistory` | Dependent test | Ordered product appears on the My Orders page |
| `loginErrorValidation` | Negative, with retry | Wrong password shows "Incorrect email or password." |
| `productErrorValidation` | Negative | Cart does not report a product that wasn't added |
| `SubmitOrder.feature` | BDD | Same purchase flow written in Gherkin |
| `ErrorValidation.feature` | BDD | Login error message in Gherkin |

## 🚀 Getting started

**Prerequisites:** JDK 11+, Maven 3.x, and Chrome, Edge or Firefox.

```bash
git clone https://github.com/nancyabcec1058/SeleniumJavaFramework.git
cd SeleniumJavaFramework
```

> **Local setup note:** file paths in `BaseTest` and `ExtentReport` use Windows separators (`\\`). On macOS or Linux, switch them to `File.separator` or `/`.

### Run tests

```bash
mvn test -PRegression                     # full suite, tests run in parallel
mvn test -PPurchase                       # purchaseOrder group
mvn test -PErrorValidation                # ErrorHandling group
mvn test -PCucumberTest                   # Cucumber BDD scenarios
mvn test -PRegression -Dbrowser=firefox   # override the browser
```

### Reports

| Report | Location |
|---|---|
| Extent Spark (TestNG) | `reports/index.html` |
| Extent Spark (Cucumber) | `test-output/SparkReport/Spark.html` |
| Cucumber HTML | `target/cucumber.html` |
| Failure screenshots | `reports/<testName>.png` |

## 👩‍💻 Author

**Nancy**, SDET / Senior QA Automation Engineer
[GitHub](https://github.com/nancyabcec1058) · [LinkedIn](https://www.linkedin.com/in/nancy1912)

> The application under test is a public demo site built for automation practice.
