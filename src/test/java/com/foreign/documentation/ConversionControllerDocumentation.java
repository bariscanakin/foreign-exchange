package com.foreign.documentation;

import com.foreign.rest.model.ConversionRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bariscanakin on 10.3.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ConversionControllerDocumentation extends BaseControllerDocumentation {

    @Before
    public void setUp() {
        setupMockMvc();
    }

    @Test
    public void doConversion() throws Exception {
        ConversionRequest request = new ConversionRequest(BigDecimal.valueOf(100d), "USD", "TRY");
        String jsonRequest = this.objectMapper.writeValueAsString(request);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/conversion").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}",
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                        PayloadDocumentation.requestFields(
                                PayloadDocumentation.fieldWithPath("amount").description("Amount to be exchanged").type(JsonFieldType.NUMBER),
                                PayloadDocumentation.fieldWithPath("currencyFrom").description("Exchange from (3 letter currency code)").type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("currencyTo").description("Exchange to (3 letter currency code)").type(JsonFieldType.STRING)
                        ),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("success").description("true/false to describe whether operation is successful or not").type(JsonFieldType.BOOLEAN),
                                PayloadDocumentation.fieldWithPath("id").description("id of conversion operation stored in database").type(JsonFieldType.NUMBER),
                                PayloadDocumentation.fieldWithPath("amount").description("Amount calculated after exchange operation").type(JsonFieldType.NUMBER)
                        )
                ));
    }

    @Test
    public void getConversionListById() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/conversionList").param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}",
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                        RequestDocumentation.requestParameters(
                                RequestDocumentation.parameterWithName("id").description("id of requested conversion")
                        ),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("success").description("true/false to describe whether operation is successful or not").type(JsonFieldType.BOOLEAN),
                                PayloadDocumentation.fieldWithPath("conversionList").description("array of conversion objects").type(JsonFieldType.ARRAY),
                                PayloadDocumentation.fieldWithPath("conversionList[].id").description("id of conversion").type(JsonFieldType.NUMBER),
                                PayloadDocumentation.fieldWithPath("conversionList[].currencyFrom").description("currency before conversion").type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("conversionList[].currencyTo").description("currency after conversion").type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("conversionList[].exchangeRate").description("exchange rate between currencies at the time of conversion").type(JsonFieldType.NUMBER),
                                PayloadDocumentation.fieldWithPath("conversionList[].amountBefore").description("amount to be exchanged in currency before conversion").type(JsonFieldType.NUMBER),
                                PayloadDocumentation.fieldWithPath("conversionList[].amountAfter").description("exchanged amount in currency after conversion").type(JsonFieldType.NUMBER),
                                PayloadDocumentation.fieldWithPath("conversionList[].conversionDate").description("conversion date formatted as yyyy-MM-dd").type(JsonFieldType.STRING)
                        )
                ));
    }

    @Test
    public void getConversionListByConversionDate() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        this.mockMvc.perform(MockMvcRequestBuilders.get("/conversionList").param("date", dateFormat.format(date)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}",
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                        RequestDocumentation.requestParameters(
                                RequestDocumentation.parameterWithName("date").description("date of requested conversions formatted as yyyy-MM-dd")
                        ),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("success").description("true/false to describe whether operation is successful or not").type(JsonFieldType.BOOLEAN),
                                PayloadDocumentation.fieldWithPath("conversionList").description("array of conversion objects").type(JsonFieldType.ARRAY),
                                PayloadDocumentation.fieldWithPath("conversionList[].id").description("id of conversion").type(JsonFieldType.NUMBER),
                                PayloadDocumentation.fieldWithPath("conversionList[].currencyFrom").description("currency before conversion").type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("conversionList[].currencyTo").description("currency after conversion").type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("conversionList[].exchangeRate").description("exchange rate between currencies at the time of conversion").type(JsonFieldType.NUMBER),
                                PayloadDocumentation.fieldWithPath("conversionList[].amountBefore").description("amount to be exchanged in currency before conversion").type(JsonFieldType.NUMBER),
                                PayloadDocumentation.fieldWithPath("conversionList[].amountAfter").description("exchanged amount in currency after conversion").type(JsonFieldType.NUMBER),
                                PayloadDocumentation.fieldWithPath("conversionList[].conversionDate").description("conversion date formatted as yyyy-MM-dd").type(JsonFieldType.STRING)
                        )
                ));
    }

}
