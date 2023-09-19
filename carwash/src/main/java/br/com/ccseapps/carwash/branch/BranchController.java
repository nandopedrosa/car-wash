package br.com.ccseapps.carwash.branch;

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
public class BranchController {

    @Autowired
    private BranchService service;

    @GetMapping("/company/{companyId}/branches")
    public List<Branch> getAllBranchesFromCompany(@PathVariable Integer companyId) {
        return service.getAllBranchesFromCompany(companyId);
    }

    @GetMapping("/branches/{id}")
    public Branch getBranch(@PathVariable Integer id) {
        Branch branch = service.getBranch(id);
        return branch;
    }

    @PostMapping("/company/{companyId}/branches")
    public Validation addBranch(@RequestBody Branch branch, @PathVariable Integer companyId) {
        return service.addBranch(branch, companyId);
    }

    @PutMapping("/company/{companyId}/branches/{branchId}")
    public Validation updateBranch(@RequestBody Branch branch, @PathVariable Integer companyId,
            @PathVariable Integer branchId) {
        return service.updateBranch(branch, companyId, branchId);
    }

    @DeleteMapping("/branch/{id}")
    public Validation deleteBranch(@PathVariable Integer id) {
        return service.deleteBranch(id);
    }
}
