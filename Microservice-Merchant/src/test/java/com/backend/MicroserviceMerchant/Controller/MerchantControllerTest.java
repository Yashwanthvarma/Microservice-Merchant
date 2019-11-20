package com.backend.MicroserviceMerchant.Controller;

import com.backend.MicroserviceMerchant.Dto.MerchantDto;
import com.backend.MicroserviceMerchant.Entity.MerchantEntity;
import com.backend.MicroserviceMerchant.Service.MerchantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class MerchantControllerTest {
    @InjectMocks
    private MerchantController merchantController;

    @Mock
    MerchantService merchantService;

    private MockMvc mockMvc;

    MerchantDto merchantDto = new MerchantDto();

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final String FIRST_NAME = "TEST";
    private static final String LAST_NAME = "CASE";
    private static final String MAIL_ID = "DEMO@TEST.COM";
    private static final String MERCHANT_CODE = "1233234342";
    private static final String MERCHANT_ID = "TEST.CASE_00";
    private static final String MERCHANT_LOCATION = "MARS";
    private static final String MOBILE_NUMBER = "123344444444";


    @Before
    public void setUp() {
//        merchantDto.setFirstName(FIRST_NAME);
//        merchantDto.setLastName(LAST_NAME);
//        merchantDto.setMailId(MAIL_ID);
//        merchantDto.setMerchantCode(MERCHANT_CODE);
//        merchantDto.setMerchantId(MERCHANT_ID);
//        merchantDto.setMerchantLocation(MERCHANT_LOCATION);
//        merchantDto.setMobileNumber(MOBILE_NUMBER);
        mockMvc = MockMvcBuilders.standaloneSetup(merchantController).build();
    }

    @After
    public void tearDown() throws Exception {
        verifyNoMoreInteractions(this.merchantService);
    }

    @Test
    public void addMerchantTest() throws Exception {
        MerchantEntity merchantEntity = new MerchantEntity();
        merchantEntity.setFirstName(FIRST_NAME);
        merchantEntity.setLastName(LAST_NAME);
        merchantEntity.setMailId(MAIL_ID);
        merchantEntity.setMerchantLocation(MERCHANT_LOCATION);
        merchantEntity.setMerchantId(MERCHANT_ID);
        merchantEntity.setMerchantCode(MERCHANT_CODE);
        merchantEntity.setMobileNumber(MOBILE_NUMBER);
        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/addNewMerchant")
                .content(OBJECT_MAPPER.writeValueAsString(merchantEntity))
                        .contentType(MediaType.APPLICATION_JSON_VALUE));
        Mockito.verify(this.merchantService).addNewMerchant(Mockito.any(merchantEntity.getClass()));

    }

    @Test
    public void updateMerchantDetailsTest() throws Exception {
        MerchantEntity merchantEntity = new MerchantEntity();
        merchantEntity.setFirstName(FIRST_NAME);
        merchantEntity.setLastName(LAST_NAME);
        merchantEntity.setMailId(MAIL_ID);
        merchantEntity.setMerchantLocation(MERCHANT_LOCATION);
        merchantEntity.setMerchantId(MERCHANT_ID);
        merchantEntity.setMerchantCode(MERCHANT_CODE);
        merchantEntity.setMobileNumber(MOBILE_NUMBER);
        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/updateMerchantDetails")
                        .content(OBJECT_MAPPER.writeValueAsString(merchantEntity))
                        .contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void deleteMerchantTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/deletemerchant" + MerchantControllerTest.MERCHANT_CODE));
    }

    @Test
    public void getMerchantDetailsTest() throws Exception {
        MerchantEntity merchantEntity = new MerchantEntity();
        this.mockMvc.perform(MockMvcRequestBuilders.get("/getMerchantDetails" + MerchantControllerTest.MERCHANT_ID));
//        Mockito.verify(this.merchantService).getmerchantDetails(Mockito.eq(OBJECT_MAPPER.writeValueAsString(merchantEntity)));
    }

    @Test
    public void getMerchantIdAvailabilityTest() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/merchantIdAvailability" + MerchantControllerTest.MERCHANT_CODE));
    }

    @Test
    public void merchantIdRecommendations() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/userIdRecommedations")
        .param("First Name",FIRST_NAME).param("Last Name",LAST_NAME));
    }
}
