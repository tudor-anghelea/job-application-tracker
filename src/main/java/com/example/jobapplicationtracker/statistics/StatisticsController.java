package com.example.jobapplicationtracker.statistics;
import com.example.jobapplicationtracker.application.Application;
import com.example.jobapplicationtracker.application.ApplicationRepository;
import com.example.jobapplicationtracker.status.Status;
import com.example.jobapplicationtracker.status.StatusChange;
import com.example.jobapplicationtracker.status.StatusChangeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private final ApplicationRepository appRepo;
    private final StatusChangeRepository statusRepo;

    public StatisticsController(ApplicationRepository appRepo, StatusChangeRepository statusRepo)
    {
        this.appRepo = appRepo;
        this.statusRepo = statusRepo;
    }

    @GetMapping("/by-location") //add ?location=Amsterdam e.g. on every endpoint
    public List<Application> filterLocation(@RequestParam String location)
    {
        return appRepo.findAllByLocationIgnoreCase(location);
    }

    @GetMapping("/by-status") //add ?status=INTERVIEW e.g. on every endpoint
    public List<Application> filterStatus(@RequestParam Status status)
    {
        return appRepo.findAllByStatus(status);
    }

    @GetMapping("/{id}/status-history")
    public List<StatusChange> applicationStatusHistory(@PathVariable Long id)
    {
        if(!appRepo.existsById(id))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found.");
        }
        return statusRepo.findAllByApplicationId(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id)
    {
        if(!statusRepo.existsById(id))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Status Change not found.");
        }
        statusRepo.delete(statusRepo.getReferenceById(id));
    }
}
