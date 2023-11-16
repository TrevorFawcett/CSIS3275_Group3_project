package com.csis3275.investment;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class Stock {
	

    @Value("${alphavantage.apikey}")
    private String apiKey;
    
    public String getStockBySymbol() {

        // Loop through symbols and fetch data for each

         String apiUrl = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=AAPL&apikey=" + apiKey;

         RestTemplate restTemplate = new RestTemplate();
         String responseData = restTemplate.getForObject(apiUrl, String.class);

		return responseData;
    }
}
