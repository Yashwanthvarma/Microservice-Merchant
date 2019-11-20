package com.backend.MicroserviceMerchant.Controller;


import com.backend.MicroserviceMerchant.Dto.MerchantDto;
import com.backend.MicroserviceMerchant.Entity.MerchantEntity;
import com.backend.MicroserviceMerchant.Service.MerchantService;
import com.backend.MicroserviceMerchant.Utils.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
public class MerchantController {

    @Autowired
    MerchantService merchantService;

    public static final Logger LOGGER = LoggerFactory.getLogger(MerchantController.class);

    @RequestMapping(value = "/addNewMerchant", method = RequestMethod.POST)
    public BaseResponse addNewMerchant (@RequestBody MerchantDto merchantDto) {
        LOGGER.info("merchant creation method has been invoked");
        MerchantEntity merchantEntity = new MerchantEntity();
        BeanUtils.copyProperties(merchantDto, merchantEntity);
        BaseResponse response =  merchantService.addNewMerchant(merchantEntity);
        return response;
    }

    @RequestMapping(value = "/deletemerchant/{merchantID}", method = RequestMethod.DELETE)
    public void deleteMerchant (@PathVariable String merchantID) {
        LOGGER.warn("merchant with id -> {} is going to be deleted", merchantID);
        merchantService.deleteMerchant(merchantID);
    }

    @RequestMapping(value = "/updateMerchantDetails", method = RequestMethod.PUT)
    public BaseResponse UpdateMerchantDetails (@RequestBody MerchantDto merchantDto) {
        MerchantEntity merchantEntity = new MerchantEntity();
        BeanUtils.copyProperties(merchantDto, merchantEntity);
        BaseResponse response = merchantService.updateMerchantDetails(merchantEntity);
        LOGGER.info("merchant details with id -> {} update invoked",merchantDto.getMerchantId());
        return response;
    }

    @RequestMapping(value = "/getMerchantDetails/{merchantID}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getMerchantDetails (@PathVariable String merchantID) {
        BaseResponse response = merchantService.getmerchantDetails(merchantID);
        return response;
    }

    @RequestMapping(value = "/merchantIdAvailability/{merchantID}", method = RequestMethod.GET)
    public boolean getMerchantIdAvailability (@PathVariable String merchantID) {
        return merchantService.getMerchantIdAvailability(merchantID);
    }

    @RequestMapping(value = "/userIdRecommedations", method = RequestMethod.GET)
    public String merchantIdRecommendations (@RequestParam(required = true) String FirstName,
    @RequestParam(required = true) String LastName) {
        return merchantService.generateMerchantId(FirstName, LastName);
    }
}
