package com.mush.spiceShop.reposiory;

import com.mush.spiceShop.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
}
