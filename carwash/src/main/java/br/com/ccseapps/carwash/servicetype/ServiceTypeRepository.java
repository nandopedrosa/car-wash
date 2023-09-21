package br.com.ccseapps.carwash.servicetype;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.ccseapps.carwash.branch.Branch;

public interface ServiceTypeRepository extends CrudRepository<ServiceType, Integer> {
    // List<ServiceType> findByBranches(Branch branch);

    public List<ServiceType> findByCompanyId(Integer companyId);

}
