package com.legion.standprojectapp.repository;

import com.legion.standprojectapp.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);

    @Modifying
    @Transactional
    @Query(value = "delete from PasswordResetToken p where p.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);
}
