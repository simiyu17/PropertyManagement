package com.property.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.property.config.ApiKeyRequestFilter;
import com.property.dao.InvestmentPropertyDaoImpl;
import com.property.model.InvestmentProperty;
import com.property.util.MessageConstant;
import java.math.BigDecimal;

import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest
class PropertyApplicationTests {

    
    private MockMvc mockMvc;

    @MockBean
    private InvestmentPropertyDaoImpl propertyDao;

    private List<InvestmentProperty> properties;

    @Value("${property.api.key}")
    private String apiKey;

    private static ObjectMapper mapper = new ObjectMapper();
    
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ApiKeyRequestFilter springSecurityFilterChain;

    private HttpHeaders getHeaders() {
        return new HttpHeaders() {
            {
                set("APIKEY", apiKey);
                set("Accept", "application/json");
                setContentType(MediaType.APPLICATION_JSON);

            }
        };
    }

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain, "/*").build();

    }

    @Test
    public void shouldReturnAllAvailableproperties() throws Exception {
        InvestmentProperty p1 = new InvestmentProperty("A560Y", "Property One", new BigDecimal(12584754), new BigDecimal(50000), "Location 1", Boolean.FALSE, Boolean.FALSE);
        InvestmentProperty p2 = new InvestmentProperty("F967Z", "Property Two", new BigDecimal(5854354), new BigDecimal(50000), "Location 2", Boolean.FALSE, Boolean.FALSE);
        InvestmentProperty p3 = new InvestmentProperty("D577P", "Property Three", new BigDecimal(67584754), new BigDecimal(50000), "Location 3", Boolean.FALSE, Boolean.FALSE);

        doReturn(List.of(p1, p2, p3)).when(this.propertyDao).getInvestmentProperties(null, null, null, null, null);

        this.mockMvc.perform(get("/properties/")
                .headers(getHeaders())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[0].code", Matchers.equalTo("A560Y")));
    }

    @Test
    public void shouldReturnPropertyById() throws Exception {
        final String propId = "thisPropId";
        InvestmentProperty prop = new InvestmentProperty("D523Q", "Property Eight", new BigDecimal(56234), new BigDecimal(76543), "Location 3", Boolean.FALSE, Boolean.FALSE);
        prop.setId(propId);
        when(propertyDao.findById(propId)).thenReturn(prop);

        this.mockMvc.perform(get("/properties/{id}", propId)
                .header("APIKEY", apiKey)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.aMapWithSize(15)))
                .andExpect(jsonPath("$.name", Matchers.equalTo("Property Eight")));

    }

    @Test
    public void shouldReturn_404_If_Property_NOT_FOUND_ById() throws Exception {
        final String propId = "thisPropIdIsPresent";
        InvestmentProperty prop = new InvestmentProperty("H523Q", "Property Five", new BigDecimal(56234), new BigDecimal(76543), "Location83", Boolean.FALSE, Boolean.FALSE);
        prop.setId(propId);
        final String propertyId_not_present = "IdThatIsNotPresent";

        when(propertyDao.findById(propId)).thenReturn(prop);

        this.mockMvc.perform(get("/properties/{id}", propertyId_not_present)
                .header("APIKEY", apiKey)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.msg", Matchers.equalTo(MessageConstant.EndpointMessage.RESOURCE_WITH_ID_NOT_FOUND + propertyId_not_present)));

    }

    @Test
    public void shouldCreateNewPropertySuccessfully() throws Exception {
        final String propId = "thisIsAnewProperty";
        InvestmentProperty prop = new InvestmentProperty("H523Q", "Property New", new BigDecimal(56234), new BigDecimal(7656543), "Location83", Boolean.FALSE, Boolean.FALSE);
        prop.setId(propId);
        String json = mapper.writeValueAsString(prop);

        when(propertyDao.save(ArgumentMatchers.any())).thenReturn(prop);

        this.mockMvc.perform(post("/properties/")
                .header("APIKEY", apiKey)
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.msg", Matchers.equalTo(MessageConstant.EndpointMessage.SAVE_SUCCESS)))
                .andExpect(jsonPath("$.success", Matchers.equalTo(true)));

    }

    @Test
    public void shouldDeleteStudent() throws Exception {
        final String propId = "thisIsProperty";
        InvestmentProperty prop = new InvestmentProperty("H523Q", "House Property", new BigDecimal(56234), new BigDecimal(7656543), "Nairobi", Boolean.FALSE, Boolean.FALSE);
        prop.setId(propId);

        when(propertyDao.findById(ArgumentMatchers.any())).thenReturn(prop);
        doNothing().when(propertyDao).delete(propId);

        this.mockMvc.perform(delete("/properties/{id}", propId).header("APIKEY", apiKey)).andExpect(status().isOk());

    }
}
