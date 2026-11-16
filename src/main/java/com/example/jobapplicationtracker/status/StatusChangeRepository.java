package com.example.jobapplicationtracker.status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusChangeRepository extends JpaRepository<StatusChange, Long> {
    List<StatusChange> findAllByApplicationId(Long applicationId);
}
