package com.legion.standprojectapp.repository;

import com.legion.standprojectapp.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);

    @Modifying
    @Transactional
    @Query(value = "delete from VerificationToken v where v.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);
}
