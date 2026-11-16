package com.example.jobapplicationtracker.status;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class StatusChange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long applicationId;

    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private LocalDate date;

    protected StatusChange() {}

    public StatusChange(Long applicationId, Status status, LocalDate date)
    {
        this.applicationId = applicationId;
        this.status = status;
        this.date = date;
    }

    public Long getApplicationId() { return this.applicationId; }
    public Status getStatus() { return this.status; }
    public LocalDate getDate() { return this.date; }
}
