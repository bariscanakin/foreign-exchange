package com.foreign.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foreign.rest.model.ErrorResponse;
import com.foreign.rest.model.RateResponse;
import com.foreign.service.RateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

/**
 * Created by bariscanakin on 10.3.2017.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(RateController.class)
public class RateControllerTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RateService rateService;

    @Test
    public void testRate() throws Exception {
        Mockito.when(this.rateService.getRate("USD", "TRY")).thenReturn(BigDecimal.valueOf(3.75d));
        RateResponse response = new RateResponse.Builder(BigDecimal.valueOf(3.75d)).build();
        String jsonResponse = this.objectMapper.writeValueAsString(response);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/rate").param("currencyFrom", "USD").param("currencyTo", "TRY"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonResponse));
    }

    @Test
    public void rateShouldReturnInternalServerErrorWhenExceptionThrown() throws Exception {
        Mockito.when(this.rateService.getRate("USD", "TRY")).thenThrow(Exception.class);
        ErrorResponse response = new ErrorResponse.Builder("Internal Server Error").build();
        String jsonResponse = this.objectMapper.writeValueAsString(response);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/rate").param("currencyFrom", "USD").param("currencyTo", "TRY"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().json(jsonResponse));
    }
}
