package com.backend.MicroserviceMerchant.Service;

import com.backend.MicroserviceMerchant.Entity.MerchantEntity;
import com.backend.MicroserviceMerchant.Utils.BaseResponse;

public interface MerchantService {

    public BaseResponse addNewMerchant (MerchantEntity merchantEntity);

    public void deleteMerchant (String merchantId);

    public BaseResponse updateMerchantDetails (MerchantEntity merchantEntity);

    public BaseResponse getmerchantDetails (String merchantId);

    public boolean getMerchantIdAvailability (String merchantId);

    public String generateMerchantId (String FirstName, String LastName);
}
