package br.com.ccseapps.carwash.servicetype;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ccseapps.carwash.company.Company;
import br.com.ccseapps.carwash.util.Status;
import br.com.ccseapps.carwash.util.Validation;

@Service
public class ServiceTypeService {

    @Autowired
    private ServiceTypeRepository repo;

    public Validation addServiceType(ServiceType serviceType, Integer companyId) {
        serviceType.setCompany(new Company(companyId));

        if (serviceType.hasNullMandatoryField()) {
            return new Validation("Please, fill the mandatory fields.", Status.ERROR);
        }
        repo.save(serviceType);
        return new Validation("Service Type successfully added.", Status.OK);
    }

    public Validation updateServiceType(ServiceType serviceType, Integer id, Integer companyId) {
        serviceType.setCompany(new Company(companyId));

        if (serviceType.hasNullMandatoryField()) {
            return new Validation("Please, fill the mandatory fields.", Status.ERROR);
        }

        serviceType.setId(id);
        repo.save(serviceType);
        return new Validation("Service Type successfully updated.", Status.OK);
    }

    public List<ServiceType> getAllServiceTypesFromCompany(Integer companyId) {
        List<ServiceType> serviceTypes = new ArrayList<>();
        repo.findByCompanyId(companyId).forEach(serviceTypes::add);
        return serviceTypes;
    }

    public ServiceType getServiceType(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public Validation deleteServiceType(Integer id) {
        repo.deleteById(id);
        return new Validation("Service Type successfully deleted.", Status.OK);
    }

}
