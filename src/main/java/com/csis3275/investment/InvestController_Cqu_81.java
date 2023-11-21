package com.csis3275.investment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class InvestController_Cqu_81 {


    @Value("${alphavantage.apikey}")
    private String apiKey;
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/invest-page")
    public String investPage(Model model) {
        model.addAttribute("symbol", "");
        model.addAttribute("name", "");
        model.addAttribute("exchange", "");
        model.addAttribute("currency", "");
        model.addAttribute("data", new ArrayList<>());
        model.addAttribute("cashFlow", new ArrayList<>());
        return "investment";
    }

    @SuppressWarnings("unchecked")
    @PostMapping("/invest-page")
    public String fetchData(@RequestParam String symbol, Model model) {
        try {
            // Fetching overview data
            String overviewUrl = "https://www.alphavantage.co/query?function=OVERVIEW&symbol=" + symbol + "&apikey=" + apiKey;
            Map<String, String> overviewData = restTemplate.getForObject(overviewUrl, Map.class);
            model.addAttribute("name", overviewData.get("Name"));
            model.addAttribute("exchange", overviewData.get("Exchange"));
            model.addAttribute("currency", overviewData.get("Currency"));

         // Fetching global quote data
            String globalQuoteUrl = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + symbol + "&apikey=" + apiKey;
            Map<String, Object> globalQuoteData = restTemplate.getForObject(globalQuoteUrl, Map.class);
            if (globalQuoteData != null && globalQuoteData.containsKey("Global Quote")) {
                Map<String, Object> quoteData = (Map<String, Object>) globalQuoteData.get("Global Quote");
                model.addAttribute("globalQuote", quoteData);
            } else {
                model.addAttribute("error", "Error fetching global quote data");
            }


        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error fetching data");
        }

        model.addAttribute("symbol", symbol);
        return "investment";
    }
}