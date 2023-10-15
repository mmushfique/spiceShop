package com.mush.spiceShop.repository;

import com.mush.spiceShop.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    @Query("SELECT a FROM Admin a WHERE a.username = :username")
    public Admin getAdminByUsername(@Param("username") String username);
}
