package com.legion.standprojectapp.repository;

import com.legion.standprojectapp.entity.EmailChangeToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface EmailChangeTokenRepository extends JpaRepository<EmailChangeToken, Long> {
    EmailChangeToken findByToken(String token);

    @Modifying
    @Transactional
    @Query(value = "delete from EmailChangeToken e where e.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);
}
