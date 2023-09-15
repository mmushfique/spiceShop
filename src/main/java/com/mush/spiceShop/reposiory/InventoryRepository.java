package com.mush.spiceShop.reposiory;

import com.mush.spiceShop.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
}
