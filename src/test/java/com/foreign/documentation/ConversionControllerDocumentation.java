package com.foreign.documentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foreign.rest.model.ConversionRequest;
import com.foreign.service.ConversionService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

/**
 * Created by bariscanakin on 10.3.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ConversionControllerDocumentation {
    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(MockMvcRestDocumentation.documentationConfiguration(this.restDocumentation)).build();
    }

    @Test
    public void documentConversionResource() throws Exception {
        doConversion();
    }

    public void doConversion() throws Exception {
        ConversionRequest request = new ConversionRequest(BigDecimal.valueOf(100d), "USD", "TRY");
        String jsonRequest = this.objectMapper.writeValueAsString(request);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/conversion").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentation.document("conversion",
                        PayloadDocumentation.requestFields(
                                PayloadDocumentation.fieldWithPath("amount").description("Amount to exchange formatted as: #.#").type(JsonFieldType.NUMBER),
                                PayloadDocumentation.fieldWithPath("currencyFrom").description("Convert from (3 letter currency code)").type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("currencyTo").description("Convert to (3 letter currency code)").type(JsonFieldType.STRING)
                        ),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("success").description("true/false to describe whether operation is successful or not"),
                                PayloadDocumentation.fieldWithPath("id").description("id of conversion operation stored in database"),
                                PayloadDocumentation.fieldWithPath("amount").description("Amount calculated after exchange operation")
                                )));
    }

}
