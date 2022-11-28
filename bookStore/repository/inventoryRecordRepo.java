package com.bookStore.bookStore.repository;

import com.bookStore.bookStore.model.books;
import com.bookStore.bookStore.model.inventoryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface inventoryRecordRepo extends JpaRepository<inventoryRecord, Long> {
    inventoryRecord findByInventoryCode(String InventoryCode);

}
