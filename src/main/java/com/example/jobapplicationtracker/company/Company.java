package com.example.jobapplicationtracker.company;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String site;

    private LocalDate createdAt;

    protected Company() {}

    public Company(String name, String site)
    {
        this.name = name;
        this.site = site;

        createdAt = LocalDate.now();
    }

    public Long getId() { return this.id; }
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
    public String getSite() { return this.site; }
    public void setSite(String site) { this.site = site; }
}
