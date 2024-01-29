package com.prakash.inventoryService.Repository;


import com.prakash.inventoryService.Entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity,Long> {
   // Optional<InventoryEntity> findBySkuCode(List<String> skuCode);

    List<InventoryEntity> findBySkuCodeIn (List<String> skuCode);
}
