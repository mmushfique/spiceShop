package com.mush.spiceShop.repository;

import com.mush.spiceShop.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    List<Authority> findAllByUserId(Long id);
}