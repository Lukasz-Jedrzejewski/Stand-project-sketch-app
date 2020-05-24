package com.legion.standprojectapp.repository;

import com.legion.standprojectapp.entity.CurrentEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentEventRepository extends JpaRepository<CurrentEvent, Long> {

}
