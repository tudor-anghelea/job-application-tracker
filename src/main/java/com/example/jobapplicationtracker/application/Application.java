package com.example.jobapplicationtracker.application;
import com.example.jobapplicationtracker.status.Status;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long companyId;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    private String location;

    private LocalDate appliedAt;

    protected Application() {}

    public Application(Long companyId, String location)
    {
        this.companyId = companyId;
        this.location = location;
        appliedAt = LocalDate.now();
        status = Status.APPLIED;
    }

    public Long getId() { return this.id; }
    public Long getCompanyId() { return this.companyId; }
    public Status getStatus() { return this.status; }
    public void setStatus(Status status) { this.status = status; }
    public String getLocation() { return this.location; }
    public void setLocation(String location) { this.location = location; }
    public LocalDate getAppliedAt() { return this.appliedAt; }
}
