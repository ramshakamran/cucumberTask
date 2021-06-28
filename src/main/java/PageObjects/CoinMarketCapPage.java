package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CoinMarketCapPage {

    WebDriver driver;
    public CoinMarketCapPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements( driver, this );
    }


    //Locators
    private static final By SHOW_ROW_BUTTON_LOCATOR = By.xpath("//*[contains(text(),'Show rows')]/following-sibling::div");
    private static final By TABLE_ROW_LOCATOR = By.cssSelector("table > tbody > tr");
    public static final By ALERT_CLOSE_BUTTON = By.xpath("//div[@class='sc-1wyjcwq-0 hELUpj']//*[local-name()='svg']");
    private static final By FILTERS_BUTTON_LOCATOR = By.xpath("//*[@id=\"__next\"]/div[1]/div[1]/div[2]/div/div/div[1]/div[2]/div[3]/div[2]/button[1]");
    private static final By ADD_FILTERS_BUTTON_LOCATOR = By.xpath("//button[normalize-space()='+ Add Filter']");
    private static final By MARKET_CAP_BUTTON_LOCATOR = By.xpath("//button[contains(text(),'Market Cap')]");
    private static final By PRICE_BUTTON_LOCATOR = By.xpath("//button[contains(text(),'Price')]");
    private static final By RANGE_FILTER_INPUT_MIN_LOCATOR = By.cssSelector("[data-qa-id='range-filter-input-min']");
    private static final By RANGE_FILTER_INPUT_MAX_LOCATOR = By.cssSelector("[data-qa-id='range-filter-input-max']");
    private static final By APPLY_FILTER_BUTTON_LOCATOR = By.xpath("//button[normalize-space()='Apply Filter']");
    private static final By SHOW_RESULTS_BUTTON_LOCATOR = By.xpath("//button[normalize-space()='Show results']");
    private static final By MARKET_CAP_CELL_LOCATOR = By.cssSelector(".sc-1ow4cwt-1");
    private static final By PRICE_CELL_LOCATOR = By.cssSelector(".price___3rj7O ");

    public void closeAlertMessage() {
        getAlertMessageCloseElement().click();
    }

    public void selectRowCount(String rowCount) {
        getShowRowButtonElement().click();
        WebElement element =driver.findElement(By.xpath("//button[contains(text(),"+ rowCount +")]"));
        element.click();
    }

    public void verifyRowCount(int rows) {
        List<WebElement> actualRows = driver.findElements(TABLE_ROW_LOCATOR);
        assertEquals(actualRows.size(), rows);
    }

    public void goToFilters() throws InterruptedException {
        getFiltersButtonElement().click();
        Thread.sleep(3000);
        getAddFilterButtonElement().click();
    }


    public void enterMarketCapRange(String min, String max) {
        getMarketCapButtonElement().click();
        getRangeFilterInputMinElement().sendKeys( min );
        getRangeFilterInputMaxElement().sendKeys( max );

    }

    public void enterPriceRange(String min, String max) {
        getPriceButtonElement().click();
        getRangeFilterInputMinElement().sendKeys( min );
        getRangeFilterInputMaxElement().sendKeys( max );
    }

    public void clickApplyFilterButton() {
        getApplyFilterButtonElement().click();
    }

    public void clickShowResultsButton() {
        getShowResultsButtonElement().click();
    }

    public void checkMarketCapWithInTheFilteredCriteria(String min, String max) throws InterruptedException {
        Thread.sleep(5000);
        List<WebElement> marketCapCells = driver.findElements(MARKET_CAP_CELL_LOCATOR);
        List<WebElement> filteredList = marketCapCells
                .stream()
                .filter(marketCapCell -> Long.parseLong(marketCapCell.getText().split("\\$")[1].replace(",", "")) < Long.parseLong(min)
                        &&
                        Long.parseLong(marketCapCell.getText().split("\\$")[1].replace(",", "")) > Long.parseLong(max))
                .collect(Collectors.toList());
        assertTrue(filteredList.isEmpty());
    }

    public void checkPriceWithInTheFilteredCriteria(String min, String max) throws InterruptedException {
        Thread.sleep(3000);
        List<WebElement> priceCells = driver.findElements(PRICE_CELL_LOCATOR);
        List<WebElement> filteredList = priceCells
                .stream()
                .filter(priceCell -> Float.parseFloat(priceCell.getText().split("\\$")[1].replace(",", "")) < Float.parseFloat(min)
                        &&
                        Float.parseFloat(priceCell.getText().split("\\$")[1].replace(",", "")) > Float.parseFloat(max))
                .collect(Collectors.toList());
        assertTrue(filteredList.isEmpty());
    }

    private WebElement getShowRowButtonElement() {
        return driver.findElement(SHOW_ROW_BUTTON_LOCATOR);
    }

    private WebElement getTableRowElement() {
        return driver.findElement(TABLE_ROW_LOCATOR);
    }

    private WebElement getAlertMessageCloseElement() {
        return driver.findElement(ALERT_CLOSE_BUTTON);
    }

    private WebElement getFiltersButtonElement() {
        return driver.findElement(FILTERS_BUTTON_LOCATOR);
    }

    private WebElement getAddFilterButtonElement() {
        return driver.findElement(ADD_FILTERS_BUTTON_LOCATOR);
    }

    private WebElement getMarketCapButtonElement() {
        return driver.findElement(MARKET_CAP_BUTTON_LOCATOR);
    }

    private WebElement getPriceButtonElement() {
        return driver.findElement(PRICE_BUTTON_LOCATOR);
    }

    private WebElement getRangeFilterInputMinElement() {
        return driver.findElement(RANGE_FILTER_INPUT_MIN_LOCATOR);
    }

    private WebElement getRangeFilterInputMaxElement() {
        return driver.findElement(RANGE_FILTER_INPUT_MAX_LOCATOR);
    }

    private WebElement getApplyFilterButtonElement() {
        return driver.findElement(APPLY_FILTER_BUTTON_LOCATOR);
    }

    private WebElement getShowResultsButtonElement() {
        return driver.findElement(SHOW_RESULTS_BUTTON_LOCATOR);
    }













}
