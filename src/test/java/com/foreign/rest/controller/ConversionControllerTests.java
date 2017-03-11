package com.foreign.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foreign.domain.model.Conversion;
import com.foreign.rest.model.ConversionListResponse;
import com.foreign.rest.model.ConversionRequest;
import com.foreign.rest.model.ConversionResponse;
import com.foreign.service.ConversionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by bariscanakin on 10.3.2017.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ConversionController.class)
public class ConversionControllerTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConversionService conversionService;

    @Test
    public void testConversion() throws Exception {
        Conversion conversion = new Conversion.Builder("USD", "TRY", BigDecimal.valueOf(3.75d),
                BigDecimal.valueOf(100d), BigDecimal.valueOf(375d)).id(1L).build();
        Mockito.when(this.conversionService.exchangeCurrency("USD", "TRY", BigDecimal.valueOf(100d))).thenReturn(conversion);
        ConversionRequest request = new ConversionRequest(BigDecimal.valueOf(100d), "USD", "TRY");
        String jsonRequest = this.objectMapper.writeValueAsString(request);
        ConversionResponse response = new ConversionResponse.Builder(1L, BigDecimal.valueOf(375d)).build();
        String jsonResponse = this.objectMapper.writeValueAsString(response);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/conversion").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonResponse));
    }

    @Test
    public void testConversionListById() throws Exception {
        List<Conversion> conversionList = Arrays.asList(new Conversion.Builder("USD", "TRY", BigDecimal.valueOf(3.75d),
                BigDecimal.valueOf(100d), BigDecimal.valueOf(375d)).id(1L).build());
        Mockito.when(this.conversionService.getListOfConversions(1L, null)).thenReturn(conversionList);
        ConversionListResponse response = new ConversionListResponse.Builder(conversionList).build();
        String jsonResponse = this.objectMapper.writeValueAsString(response);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/conversionList?id={id}", 1L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonResponse));
    }

    @Test
    public void testConversionListByDate() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = "2017-02-28";
        Date date = dateFormat.parse(dateString);
        List<Conversion> conversionList = Arrays.asList(new Conversion.Builder("USD", "TRY", BigDecimal.valueOf(3.75d),
                        BigDecimal.valueOf(100d), BigDecimal.valueOf(375d)).id(1L).build(),
                new Conversion.Builder("USD", "TRY", BigDecimal.valueOf(3.75d),
                        BigDecimal.valueOf(100d), BigDecimal.valueOf(375d)).id(2L).build());
        Mockito.when(this.conversionService.getListOfConversions(null, date)).thenReturn(conversionList);
        ConversionListResponse response = new ConversionListResponse.Builder(conversionList).build();
        String jsonResponse = this.objectMapper.writeValueAsString(response);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/conversionList?date={date}", dateString))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonResponse));
    }
}
