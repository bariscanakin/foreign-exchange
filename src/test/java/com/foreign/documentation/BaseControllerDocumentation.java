package com.foreign.documentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.UriConfigurer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by bariscanakin on 11.3.2017.
 */
public abstract class BaseControllerDocumentation {

    private static final String RESTDOCS_SCHEME_KEY = "RESTDOCS_SCHEME";
    private static final String RESTDOCS_URL_KEY = "RESTDOCS_URL";
    private static final String RESTDOCS_PORT_KEY = "RESTDOCS_PORT";

    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected WebApplicationContext context;

    protected MockMvc mockMvc;

    protected void setupMockMvc() {
        UriConfigurer uriConfigurer = MockMvcRestDocumentation.documentationConfiguration(this.restDocumentation).uris();

        String restdocsScheme = System.getenv(RESTDOCS_SCHEME_KEY);
        String restdocsUrl = System.getenv(RESTDOCS_URL_KEY);
        String restdocsPort = System.getenv(RESTDOCS_PORT_KEY);

        if (restdocsScheme != null) {
            uriConfigurer.withScheme(restdocsScheme);
        }
        if (restdocsUrl != null) {
            uriConfigurer.withHost(restdocsUrl);
        }
        if (restdocsPort != null) {
            uriConfigurer.withPort(Integer.valueOf(restdocsPort));
        }

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(uriConfigurer).build();
    }

}
