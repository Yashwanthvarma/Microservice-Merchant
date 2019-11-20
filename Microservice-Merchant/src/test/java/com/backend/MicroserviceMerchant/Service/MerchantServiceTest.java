package com.backend.MicroserviceMerchant.Service;

import com.backend.MicroserviceMerchant.Entity.MerchantEntity;
import com.backend.MicroserviceMerchant.Repository.MerchantRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class MerchantServiceTest {

    @Mock
    private MerchantRepository merchantRepository;

    @Mock
    private MerchantService merchantService;

    @InjectMocks
    private MerchantServiceImpl merchantServiceimpl = new MerchantServiceImpl();

    @Before
    public void setUp() throws Exception {
        merchantEntity.setFirstName(FIRST_NAME);
        merchantEntity.setLastName(LAST_NAME);
        merchantEntity.setMailId(MAIL_ID);
        merchantEntity.setMerchantCode(MERCHANT_CODE);
        merchantEntity.setMerchantId(MERCHANT_ID);
        merchantEntity.setMerchantLocation(MERCHANT_LOCATION);
        merchantEntity.setMobileNumber(MOBILE_NUMBER);

    }

    @After
    public void tearDown() throws Exception {
        Mockito.verifyNoMoreInteractions(merchantRepository);
        Mockito.verifyNoMoreInteractions(merchantService);

    }

    private static final String FIRST_NAME = "TEST";
    private static final String LAST_NAME = "CASE";
    private static final String MAIL_ID = "DEMO@TEST.COM";
    private static final String MERCHANT_CODE = "1233234342";
    private static final String MERCHANT_ID = "TEST.CASE_00";
    private static final String MERCHANT_LOCATION = "MARS";
    private static final String MOBILE_NUMBER = "123344444444";

    private MerchantEntity merchantEntity = new MerchantEntity();


    @Test
    public void addNewMerchantTest() {
        Mockito.doThrow(IllegalStateException.class).when(merchantRepository).save(merchantEntity);
        try {
            this.merchantServiceimpl.addNewMerchant(merchantEntity);
        }
        catch (Exception e) {
            Mockito.verify(this.merchantRepository).save(merchantEntity);
        }
    }

    @Test
    public void updateMerchantTest() {
        merchantEntity.setMerchantCode(MERCHANT_CODE);
        Mockito.when(this.merchantRepository.save(merchantEntity)).thenReturn(merchantEntity);
        this.merchantServiceimpl.updateMerchantDetails(merchantEntity);
        Mockito.verify(this.merchantRepository, Mockito.times(1)).save(merchantEntity);
    }

    @Test
    public void getMerchantDetailsTest() {
        Mockito.when(this.merchantRepository.findByMerchantId(MERCHANT_ID)).thenReturn(merchantEntity);
        this.merchantServiceimpl.getmerchantDetails(MERCHANT_ID);
        Mockito.verify(this.merchantRepository, Mockito.times(1)).findByMerchantId(MERCHANT_ID);
    }

    @Test
    public void getMerchantIdAvailabilityTest() {
        Mockito.when(this.merchantRepository.findByMerchantId(MERCHANT_CODE)).thenReturn(merchantEntity);
        this.merchantServiceimpl.getMerchantIdAvailability(MERCHANT_CODE);
        Mockito.verify(this.merchantRepository, Mockito.times(1)).findByMerchantId(MERCHANT_CODE);
    }

//    @Test
//    public void generateMerchantIdTest() {
//        this.merchantServiceimpl.generateMerchantId(FIRST_NAME,LAST_NAME);
//        Mockito.verify(this.merchantRepository, Mockito.times(1)).findById("");
//    }
}
