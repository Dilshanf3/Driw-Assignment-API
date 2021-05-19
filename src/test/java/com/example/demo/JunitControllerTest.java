package com.example.demo;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.example.demo.services.ItemService;




@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class JunitControllerTest {

@Autowired
MockMvc mockMvc;
@MockBean
ItemService applicationService;


@Test
public void testGetItems() throws Exception {
	
    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/items");
    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    System.out.println(result.getResponse());
    Assert.assertEquals(200, result.getResponse().getStatus());




}


@Test
public void testFaliuregetPrice() throws Exception {
	String apiUrl = "/calculate_price/single/{itemName}/{itemId}/{amount}";
    RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl, "test", 1,234);
    mockMvc.perform(rb)
    .andExpect(status().is(404))
    .andDo(MockMvcResultHandlers.print());

}


@Test
public void testgetFaliureTotalPrice() throws Exception {
	
	Object items = new String[] {"itemId : 1", "ammount :23"};

	String apiUrl = "/items/calculate_price/all";
    RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl);
    mockMvc.perform(rb)
    .andExpect(
    		status().is(400))
    .andDo(MockMvcResultHandlers.print());





}
@Test
public void testFaliureAddItem() throws Exception {
	
	Object items = new String[] {"itemName: ds",
		"minCartoonAmountToDiscount: 2",
			"noOfUnitsInCartoon: 230",
			"priceOFSingleCartoon: 175"};

	String apiUrl = "/items/calculate_price/all";
    RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl);
    mockMvc.perform(rb)
    .andExpect(
    		status().is(400))
    .andDo(MockMvcResultHandlers.print());

}
}