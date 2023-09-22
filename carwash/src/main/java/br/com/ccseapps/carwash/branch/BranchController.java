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

import br.com.ccseapps.carwash.servicetype.ServiceType;
import br.com.ccseapps.carwash.util.Validation;
import jakarta.annotation.security.RolesAllowed;

@RestController
public class BranchController {

    @Autowired
    private BranchService service;

    @RolesAllowed({"ADMIN", "CUSTOMER"})
    @GetMapping("/companies/{companyId}/branches")
    public List<Branch> getAllBranchesFromCompany(@PathVariable Integer companyId) {
        return service.getAllBranchesFromCompany(companyId);
    }

    @RolesAllowed({"ADMIN", "CUSTOMER"})
    @GetMapping("companies/{companyId}/branches/{branchId}/serviceTypes")
    public List<ServiceType> getAllServiceTypesFromBranch(@PathVariable Integer companyId, @PathVariable Integer branchId) {
        return service.getAllServiceTypesFromBranch(branchId);
    }

    @RolesAllowed({"ADMIN", "CUSTOMER"})
    @GetMapping("/companies/{companyId}/branches/{branchId}")
    public Branch getBranch(@PathVariable Integer companyId, @PathVariable Integer branchId) {
        Branch branch = service.getBranch(branchId);
        return branch;
    }

    @RolesAllowed("ADMIN")
    @PostMapping("/companies/{companyId}/branches")
    public Validation addBranch(@RequestBody Branch branch, @PathVariable Integer companyId) {
        return service.addBranch(branch, companyId);
    }

    @RolesAllowed("ADMIN")
    @PutMapping("/companies/{companyId}/branches/{branchId}")
    public Validation updateBranch(@RequestBody Branch branch, @PathVariable Integer companyId,
            @PathVariable Integer branchId) {
        return service.updateBranch(branch, companyId, branchId);
    }

    @RolesAllowed("ADMIN")
    @DeleteMapping("/companies/{companyId}/branches/{branchId}")
    public Validation deleteBranch(@PathVariable Integer companyId, @PathVariable Integer branchId) {
        return service.deleteBranch(branchId);
    }

    @RolesAllowed("ADMIN")
    @PostMapping("companies/{companyId}/branches/{branchId}/servicetypes/{serviceTypeId}")
    public Validation addServiceTypeToBranch(@PathVariable Integer companyId, @PathVariable Integer branchId, @PathVariable Integer serviceTypeId) {
        return service.addServiceTypeToBranch(branchId, serviceTypeId);
    }

}
