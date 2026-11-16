package com.example.jobapplicationtracker.company;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyRepository repo;

    public CompanyController(CompanyRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Company> list() { return repo.findAll(); }

    @GetMapping("/{id}")
    public Company get(@PathVariable Long id)
    {
        return repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found."));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company create(@RequestBody Company company)
    {
        if(repo.existsByName(company.getName()))
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Company already exists.");
        }
        return repo.save(company);
    }

    @PutMapping("/{id}")
    public Company update(@PathVariable Long id, @RequestBody Company updated)
    {
        Company company = get(id);

        company.setName(updated.getName());
        company.setSite(updated.getSite());

        return repo.save(company);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id)
    {
        if(!repo.existsById(id))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found.");
        }
        repo.deleteById(id);
    }
}
