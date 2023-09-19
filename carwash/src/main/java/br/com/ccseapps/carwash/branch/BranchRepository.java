package br.com.ccseapps.carwash.branch;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BranchRepository extends CrudRepository<Branch, Integer> {
    public List<Branch> findByCompanyId(Integer companyId);
}