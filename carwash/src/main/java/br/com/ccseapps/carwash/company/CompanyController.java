package br.com.ccseapps.carwash.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ccseapps.carwash.util.Validation;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService service;

    @GetMapping("/companies")
    public List<Company> getAllCompanies() {
        return service.getAllCompanies();
    }

    @GetMapping("/companies/{id}")
    public Company getCompany(@PathVariable Integer id) {
        return service.getCompany(id);
    }

    @PostMapping("/companies")
    public Validation addCompany(@RequestBody Company c) {
        return service.addCompany(c);
    }

    @PutMapping("/companies/{id}")
    public Validation updateCompany(@RequestBody Company c, @PathVariable Integer id) {
        return service.updateCompany(c, id);
    }

    @DeleteMapping("/companies/{id}")
    public Validation deleteCompany(@PathVariable Integer id) {
        return service.deleteCompany(id);
    }

}
