package com.backend.MicroserviceMerchant.Repository;

import com.backend.MicroserviceMerchant.Entity.MerchantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<MerchantEntity, String> {
    public MerchantEntity findByMerchantId(String merchantId);

}
