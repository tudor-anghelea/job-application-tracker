package com.example.jobapplicationtracker.application;
import com.example.jobapplicationtracker.status.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findAllByStatus(Status status);

    List<Application> findAllByLocationIgnoreCase(String location);
}
