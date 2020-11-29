package com.legion.standprojectapp.repository;

import com.legion.standprojectapp.entity.PasswordChangeToken;
import com.legion.standprojectapp.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface PasswordChangeTokenRepository extends JpaRepository<PasswordChangeToken, Long> {
    PasswordChangeToken findByToken(String token);

    @Modifying
    @Transactional
    @Query(value = "delete from PasswordChangeToken p where p.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);
}
