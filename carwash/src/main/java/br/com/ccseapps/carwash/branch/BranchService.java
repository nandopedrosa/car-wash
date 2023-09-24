package br.com.ccseapps.carwash.branch;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ccseapps.carwash.company.Company;
import br.com.ccseapps.carwash.servicetype.ServiceType;
import br.com.ccseapps.carwash.servicetype.ServiceTypeRepository;
import br.com.ccseapps.carwash.servicetype.ServiceTypeService;
import br.com.ccseapps.carwash.util.Status;
import br.com.ccseapps.carwash.util.Validation;

@Service
public class BranchService {

    @Autowired
    private BranchRepository repo;

    @Autowired
    private ServiceTypeRepository serviceTypeRepo;

    @Autowired
    private ServiceTypeService serviceTypeService;

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

    public Validation addServiceTypeToBranch(Integer branchId, Integer serviceTypeId) {
        Branch b = this.getBranch(branchId);
        ServiceType st = serviceTypeService.getServiceType(serviceTypeId);
        b.addServiceType(st);
        // st.addBranch(b);
        repo.save(b);
        return new Validation("Service Type successfully added to Branch", Status.OK);
    }

    public List<ServiceType> getAllServiceTypesFromBranch(Integer branchId) {
        List<ServiceType> serviceTypes = new ArrayList<>();
        Branch branch = getBranch(branchId);
        serviceTypeRepo.findByBranches(branch).forEach(serviceTypes::add);
        return serviceTypes;
    }

    public List<String> getAllSchedulesForServiceType(Integer branchId, Integer serviceTypeId, String date) {
        List<String> res = new ArrayList<>();
        
        Branch branch = this.getBranch(branchId);
        ServiceType st = serviceTypeService.getServiceType(serviceTypeId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);

        List<LocalDateTime> times = branch.getAllSchedulesForServiceType(st, localDate);
        for(LocalDateTime ldt : times)   {
            // add 0 padding
            String minute = ldt.getMinute() == 0 ? "00" : String.valueOf(ldt.getMinute());
            String s = ldt.getHour() + ":" + minute;
            res.add(s);
        }
        return res;
    }

}
