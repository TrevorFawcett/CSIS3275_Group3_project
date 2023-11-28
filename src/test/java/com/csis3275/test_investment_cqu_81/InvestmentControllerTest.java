package com.csis3275.test_investment_cqu_81;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.csis3275.Credit.model.CreditServiceImpl;
import com.csis3275.investment.InvestController_Cqu_81;




@WebMvcTest(InvestController_Cqu_81.class)
@ComponentScan("com.csis3275.Credit.model")
class InvestmentControllerTest {
	
	
	
	@MockBean
	CreditServiceImpl creditService;
	
	
	@Autowired
	private MockMvc mvc;
	
	
	
	/**
	 * 
			@author CarlosQuintero - 300353381 
			 Testing if the controller works and return the view in /templates/investment.html once the "invest-page" GET request is called
			 
			
	 */
	@Test
	void TestInvestmentFormGet() throws Exception	{
		mvc.perform(MockMvcRequestBuilders
				.get("/invest-page")
				.accept(MediaType.TEXT_HTML)) //From springframework.http
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("creditId"))
				.andExpect(model().attributeExists("symbol"))
				.andExpect(model().attributeExists("name"))
				.andExpect(model().attributeExists("exchange"))
				.andExpect(model().attributeExists("currency"))
				.andExpect(model().attributeExists("data"))
				.andExpect(model().attributeExists("cashflow"))
				.andExpect(MockMvcResultMatchers.view().name("investment"));
	}
	

	/**
	 * 
			@author CarlosQuintero - 300353381 
			 Testing if the controller works and return the view in /templates/investment.html once the "invest-page" POST request is called
			 
			 Note: if null values are returned is because the API has a limit of 25 requests per day for free users
			 
	 */
	@Test
	public void TestInvestmentFormPost() throws Exception {
		mvc.perform( MockMvcRequestBuilders
				.post("/invest-page")
				.param("symbol", "MSFT")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.accept(MediaType.TEXT_HTML))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("symbol"))
		.andExpect(model().attributeExists("name"))
		.andExpect(model().attributeExists("exchange"))
		.andExpect(model().attributeExists("currency"))
		.andExpect(model().attributeExists("data"))
		.andExpect(model().attributeExists("cashflow"));

	}
	
	/**
	 * 
			@author CarlosQuintero - 300353381 
			 Testing if the controller works and return the view in /templates/user-page once the "/invest/add/{creditId} POST request is called

	 */
	
	@Test
	public void TestBuyShares() throws Exception {
		mvc.perform( MockMvcRequestBuilders
				.post("/invest/add/")
				.param("price", "378.61")
				.param("shares", "4")
				.param("creditId", "1")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.accept(MediaType.TEXT_HTML))
		.andDo(print())
		.andExpect(status().isOk());

	}
	

}
