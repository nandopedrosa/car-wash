package br.com.ccseapps.carwash.branch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ccseapps.carwash.company.Company;
import br.com.ccseapps.carwash.util.Status;
import br.com.ccseapps.carwash.util.Validation;

@Service
public class BranchService {

    @Autowired
    private BranchRepository repo;

    public Validation addBranch(Branch branch, Integer companyId) {
        branch.setCompany(new Company(companyId));

        if (branch.hasNullMandatoryField()) {
            return new Validation("Please, fill the mandatory fields.", Status.ERROR);
        }
        repo.save(branch);
        return new Validation("Branch successfully added.", Status.OK);
    }

    public Validation updateBranch(Branch branch, Integer companyId, Integer branchId) {
        branch.setCompany(new Company(companyId));

        if (branch.hasNullMandatoryField()) {
            return new Validation("Please, fill the mandatory fields.", Status.ERROR);
        }

        branch.setId(branchId);
        repo.save(branch);
        return new Validation("Branch successfully updated.", Status.OK);
    }

    public List<Branch> getAllBranchesFromCompany(Integer companyId) {
        List<Branch> branches = new ArrayList<>();
        repo.findByCompanyId(companyId).forEach(branches::add);
        return branches;
    }

    public Branch getBranch(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public Validation deleteBranch(Integer id) {
        repo.deleteById(id);
        return new Validation("Branch successfully deleted.", Status.OK);
    }

}
