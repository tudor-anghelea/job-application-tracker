package com.example.jobapplicationtracker.application;
import com.example.jobapplicationtracker.status.Service;
import com.example.jobapplicationtracker.company.CompanyRepository;
import com.example.jobapplicationtracker.status.Status;
import com.example.jobapplicationtracker.status.StatusChange;
import com.example.jobapplicationtracker.status.StatusChangeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationController {

    private final ApplicationRepository appRepo;
    private final CompanyRepository companyRepo;
    private final StatusChangeRepository statusRepo;

    public ApplicationController(ApplicationRepository appRepo, CompanyRepository companyRepo, StatusChangeRepository statusRepo)
    {
        this.appRepo = appRepo;
        this.companyRepo = companyRepo;
        this.statusRepo = statusRepo;
    }

    @GetMapping
    public List<Application> list() { return appRepo.findAll(); }

    @GetMapping("/{id}")
    public Application getById(@PathVariable Long id)
    {
        return appRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found."));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Application create(@RequestBody Application application)
    {
        if(!companyRepo.existsById(application.getCompanyId()))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found.");
        }
        statusRepo.save(new StatusChange(application.getId(), Status.APPLIED, LocalDate.now()));

        return appRepo.save(application);
    }

    @PutMapping("/{id}/update-location")
    public Application updateLocation(@PathVariable Long id, @RequestBody String location) //only "string" etc
    {
        Application application = getById(id);
        application.setLocation(location);

        return appRepo.save(application);
    }

    @PutMapping("/{id}/update-status")
    public Application updateStatus(@PathVariable Long id, @RequestBody Status status) //only "APPLIED" etc
    {
        Application application = getById(id);
        if(!Service.isAllowed(application.getStatus(), status))
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Status change is invalid.");
        }
        application.setStatus(status);
        statusRepo.save(new StatusChange(id, status, LocalDate.now()));

        return appRepo.save(application);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id)
    {
        if(!appRepo.existsById(id))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found.");
        }
        appRepo.deleteById(id);
    }
}
