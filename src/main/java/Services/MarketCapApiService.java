package Services;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MarketCapApiService {

    String API_Key = "ac03619c-90a0-467e-843f-cda759db21e7";

    private static final String BASE_URL = "https://pro-api.coinmarketcap.com/v1";
    private static final String CRYPTOCURRENCY_MAPURL_PART = "/cryptocurrency/map";
    private static final String TOOL_PRICE_CONVERSION_PART = "/tools/price-conversion";
    private static final String CRYPTOCURRENCY_INFO_PART = "/cryptocurrency/info";
    public static final String API_KEY_NAME = "X-CMC_PRO_API_KEY";

    public Response getCryptoCurrencyMap() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification httpRequest = RestAssured
                .given()
                .header("X-CMC_PRO_API_KEY", API_Key);
        return httpRequest
                .request(Method.GET, CRYPTOCURRENCY_MAPURL_PART);
    }

    public Response getPriceConversion(int amount, int id) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification httpRequest = RestAssured
                .given()
                .header("X-CMC_PRO_API_KEY", API_Key)
                .queryParam("amount", amount)
                .queryParam("id", id);
        return httpRequest
                .request(Method.GET, TOOL_PRICE_CONVERSION_PART);
    }

    public Response getCryptoCurrencyInfo(int id) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification httpRequest = RestAssured
                .given()
                .header("X-CMC_PRO_API_KEY", API_Key)
                .queryParam("id", id);
        return httpRequest
                .request(Method.GET, CRYPTOCURRENCY_INFO_PART);
    }


    public Response getCryptoCurrencyInfoOf10Coins(String ids) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification httpRequest = RestAssured
                .given()
                .header(API_KEY_NAME, API_Key)
                .queryParam("id", ids);
        return httpRequest
                .request(Method.GET, CRYPTOCURRENCY_INFO_PART);
    }
}
