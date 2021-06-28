package Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CryptoCurrencyMapResponseData {
    @JsonProperty("status")
    private CryptoCurrencyMapStatusResponseData status;
    @JsonProperty("data")
    private List<CryptoCurrencyMapDataResponseData> data;

    @JsonProperty("status")
    public CryptoCurrencyMapStatusResponseData getStatus() {
        return status;
    }

    @JsonProperty("data")
    public List<CryptoCurrencyMapDataResponseData> getData() {
        return data;
    }

    @JsonProperty("status")
    public void setStatus(CryptoCurrencyMapStatusResponseData status) {
        this.status = status;
    }

    @JsonProperty("data")
    public void setData(List<CryptoCurrencyMapDataResponseData> data) {
        this.data = data;
    }
}
