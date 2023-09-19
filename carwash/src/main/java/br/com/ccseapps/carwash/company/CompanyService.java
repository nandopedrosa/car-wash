package br.com.ccseapps.carwash.company;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ccseapps.carwash.util.Status;
import br.com.ccseapps.carwash.util.Validation;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repo;

    public Validation addCompany(Company c) {
        if (c.hasNullMandatoryField()) {
            return new Validation("Please, fill the mandatory fields.", Status.ERROR);
        }

        repo.save(c);
        return new Validation("Company successfully added.", Status.OK);
    }

    public Validation updateCompany(Company c, Integer id) {
        if (c.hasNullMandatoryField()) {
            return new Validation("Please, fill the mandatory fields.", Status.ERROR);
        }

        c.setId(id);
        repo.save(c);
        return new Validation("Company successfully updated.", Status.OK);
    }

    public List<Company> getAllCompanies() {
        List<Company> companies = new ArrayList<>();
        repo.findAll().forEach(companies::add);
        return companies;
    }

    public Company getCompany(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public Validation deleteCompany(Integer id) {
        repo.deleteById(id);
        return new Validation("Company successfully deleted.", Status.OK);
    }

}
