package com.foreign.documentation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Created by bariscanakin on 10.3.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class RateControllerDocumentation extends BaseControllerDocumentation {

    @Before
    public void setUp() {
        setupMockMvc();
    }

    @Test
    public void getExchangeRate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/rate").param("currencyFrom", "USD").param("currencyTo", "TRY"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}",
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                        RequestDocumentation.requestParameters(
                                RequestDocumentation.parameterWithName("currencyFrom").description("Exchange from (3 letter currency code)"),
                                RequestDocumentation.parameterWithName("currencyTo").description("Exchange to (3 letter currency code)")
                        ),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("success").description("true/false to describe whether operation is successful or not").type(JsonFieldType.BOOLEAN),
                                PayloadDocumentation.fieldWithPath("exchangeRate").description("exchange rate between currencies").type(JsonFieldType.NUMBER)
                        )
                ));
    }
}
