package stepDefinitions;

import General.BaseTest;
import Models.CryptoCurrencyMapDataResponseData;
import Models.CryptoCurrencyMapResponseData;
import Services.MarketCapApiService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.runner.RunWith;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Cucumber.class)
public class StepDefinitionAPI extends BaseTest {
    private Response response;
    private Integer cryptoId;
    private JSONObject ethereum;
    private ArrayList<String> mineableCoins;
    private JSONObject data;


    @Given("Request send to /cryptocurrency/map")
    public void create_the_request() {
        response = new MarketCapApiService().getCryptoCurrencyMap();
    }

    @When("The status code is 200")
    public void request_send_to_cryptocurrency_map() {
        assertEquals(response.statusCode(), HttpURLConnection.HTTP_OK);
    }

    @Then("^Get the ids of \"([^\"]*)\"$")
    public void all_IDs_should_be_retrieved(String coinName) {
        CryptoCurrencyMapResponseData responseData = response.then().extract().as(CryptoCurrencyMapResponseData.class);
        CryptoCurrencyMapDataResponseData data = responseData.getData().stream()
                .filter(element -> Objects.equals(element.getName(), coinName)).findFirst().get();
        cryptoId = data.getId();
    }

    @Then("^Convert the currency for a amount of \"([^\"]*)\"$")
    public void convert_the_currency_for_a_amount_of(int amount) {
        response = new MarketCapApiService().getPriceConversion(amount, cryptoId);
        String responseBody = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONObject data = jsonObject.getJSONObject("data");
        System.out.println("Converted price is $" +
                data.getJSONObject("quote").getJSONObject("USD").get("price").toString());
    }

    @Given("^Request send to /cryptocurrency/info with id \"([^\"]*)\"$")
    public void request_send_to_cryptocurrency_info_with_id(int id) {
        response = new MarketCapApiService().getCryptoCurrencyInfo(id);
        String responseBody = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONObject data = jsonObject.getJSONObject("data");
        ethereum = data.getJSONObject("1027");
    }

    @Then("^Check logo url$")
    public void check_logo_url() {
        assertEquals(ethereum.getString("logo"),
                "https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png");
    }

    @Then("^Check technical doc url$")
    public void check_technical_doc_url() {
        assertEquals(ethereum.getJSONObject("urls").get("technical_doc").toString(),
                "[\"https://github.com/ethereum/wiki/wiki/White-Paper\"]");
    }

    @Then("^Check the symbol$")
    public void check_the_symbol() {
        assertEquals(ethereum.getString("symbol"), "ETH");
    }

    @Then("^Check the date added$")
    public void check_the_date_added() {
        assertEquals(ethereum.getString("date_added"), "2015-08-07T00:00:00.000Z");
    }

    @Then("^check  for the mineable tag$")
    public void check_for_the_mineable_tag() {
        assertTrue(ethereum.getJSONArray("tags").toList().contains("mineable"));
    }

    @Given("^Request send to /cryptocurrency/info for first 10 ids$")
    public void request_send_to_cryptocurrency_info_for_first_ids() {
        response = new MarketCapApiService().getCryptoCurrencyInfoOf10Coins("1,2,3,4,5,6,7,8,9,10");
    }

    @Then("^Check  for the mineable tag in response cryptocurrencies$")
    public void check_for_the_mineable_tag_in_response_cryptocurrencies() {
        String responseBody = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(responseBody);
        data = jsonObject.getJSONObject("data");
        try {
            Iterator<String> keys = data.keys();
            mineableCoins = new ArrayList<>();
            while (data.keys().hasNext()) {
                String key = keys.next();
                if (data.getJSONObject(key).getJSONArray("tags").toList().contains("mineable")) {
                    mineableCoins.add(data.getJSONObject(key).getString("name"));
                }
            }
        } catch (NoSuchElementException e) {

        }
    }

    @Then("^Print mineable currency names$")
    public void print_mineable_currency_names() {
        for (String coin : mineableCoins) {
            System.out.println(coin);
        }
    }
}
