package com.backend.MicroserviceMerchant.Service;

import com.backend.MicroserviceMerchant.Dto.MerchantDto;
import com.backend.MicroserviceMerchant.Entity.MerchantEntity;
import com.backend.MicroserviceMerchant.Repository.MerchantRepository;
import com.backend.MicroserviceMerchant.Utils.BaseResponse;
import com.backend.MicroserviceMerchant.Utils.IdGeneration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Map;
import java.util.Optional;


@Service
@ControllerAdvice
public class MerchantServiceImpl implements MerchantService {

    public static final Logger Log  = LoggerFactory.getLogger(MerchantServiceImpl.class);

    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public BaseResponse addNewMerchant(MerchantEntity merchantEntity) {
        BaseResponse response;
        try {
            if (getMerchantIdAvailability(merchantEntity.getMerchantId())) {
                Validations(merchantEntity);
                merchantRepository.save(merchantEntity);
                Log.info("merchant details saved in the DB succesfully");
                response = new BaseResponse("merchant created succesfully", "", true);
            } else {
                Log.info("failed to create merchant as the record is duplicate");
                response = new BaseResponse("merchant already exist","",false);
            }
        } catch (IllegalArgumentException iae) {
            Log.error("merchant with id -> {} cannot be created because of {} is empty", merchantEntity.getMerchantId(), iae.getMessage());
            response = new BaseResponse(iae.getMessage()+" cant be empty","cannot create merchant",false);
        }
        return response;
    }

    @Override
    public void deleteMerchant(String merchantId) {
        merchantRepository.deleteById(merchantId);
    }

    @Override
    public BaseResponse updateMerchantDetails(MerchantEntity merchantEntity) {
        BaseResponse response;
        try {
            Validations(merchantEntity);
            merchantRepository.save(merchantEntity);
            Log.info("merchant details updated in the DB succesfully");
            response = new BaseResponse("merchant details updated succesfully", "", true);
        } catch (IllegalArgumentException iae) {
            Log.error("merchant details with id -> {} cannot be updated because of {} is empty", merchantEntity.getMerchantId(), iae.getMessage());
            response = new BaseResponse(iae.getMessage()+" cant be empty","cannot update merchant details",false);
        }
        return response;
    }

    @Override
    public BaseResponse getmerchantDetails(String merchantId) {
        MerchantEntity value;
        value = merchantRepository.findByMerchantId(merchantId);
        MerchantDto merchantDto;
        BaseResponse<MerchantDto> response;
        try {
            MerchantEntity merchantEntity = value;
             merchantDto = new MerchantDto();
            BeanUtils.copyProperties(merchantEntity, merchantDto);
            response = new BaseResponse<>(merchantDto,"",true, HttpStatus.OK);
            Log.info("merchant found with given id -> {}", merchantId);
        } catch (IllegalArgumentException iae) {
            Log.error("no record found for given merchant id -> {}",merchantId);
            return new BaseResponse<String>("Merchant is not found", iae.getMessage(), false ,HttpStatus.OK);
        }
        return response;
    }

    @Override
    public boolean getMerchantIdAvailability(String merchantId) {
        MerchantEntity value = merchantRepository.findByMerchantId(merchantId);
        if (value != null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String generateMerchantId(String FirstName, String LastName) {
        boolean validId = false;
        String generatedId = "";
        while (!validId) {
            generatedId = IdGeneration.generateMerchantId(FirstName, LastName);
            validId = getMerchantIdAvailability(generatedId);
        }
        return generatedId;
    }

    private void Validations (MerchantEntity merchantEntity) {
        ObjectMapper createMap = new ObjectMapper();
        Map<String, String> validationMap = createMap.convertValue(merchantEntity,Map.class);
        for (Map.Entry<String, String> entry : validationMap.entrySet()){
            if (entry.getKey() != "merchantCode" && StringUtils.isEmpty(entry.getValue())){
                Log.error("merchant create data validation failed in Validations method");
                throw new IllegalArgumentException(entry.getKey());
            }
        }
    }
}
