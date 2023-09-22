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
import jakarta.annotation.security.RolesAllowed;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService service;

    @RolesAllowed({"ADMIN", "CUSTOMER"})
    @GetMapping("/companies")
    public List<Company> getAllCompanies() {
        return service.getAllCompanies();
    }

    @RolesAllowed({"ADMIN", "CUSTOMER"})
    @GetMapping("/companies/{id}")
    public Company getCompany(@PathVariable Integer id) {
        return service.getCompany(id);
    }

    @RolesAllowed({"ADMIN"})
    @PostMapping("/companies")    
    public Validation addCompany(@RequestBody Company c) {
        return service.addCompany(c);
    }

    @RolesAllowed("ADMIN")
    @PutMapping("/companies/{id}")
    public Validation updateCompany(@RequestBody Company c, @PathVariable Integer id) {
        return service.updateCompany(c, id);
    }

    @RolesAllowed("ADMIN")
    @DeleteMapping("/companies/{id}")
    public Validation deleteCompany(@PathVariable Integer id) {
        return service.deleteCompany(id);
    }

}
