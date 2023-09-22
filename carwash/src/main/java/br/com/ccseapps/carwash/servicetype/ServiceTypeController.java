package br.com.ccseapps.carwash.servicetype;

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
public class ServiceTypeController {

    @Autowired
    private ServiceTypeService service;
      
    @RolesAllowed({"ADMIN", "CUSTOMER"})
    @GetMapping("/companies/{companyId}/servicetypes")
    public List<ServiceType> getAllServiceTypesFromCompany(@PathVariable Integer companyId) {
        return service.getAllServiceTypesFromCompany(companyId);
    }

    @RolesAllowed("ADMIN")
    @PostMapping("/companies/{companyId}/servicetypes")
    public Validation addServiceType(@RequestBody ServiceType serviceType, @PathVariable Integer companyId) {
        return service.addServiceType(serviceType, companyId);
    }

    @RolesAllowed("ADMIN")
    @PutMapping("/companies/{companyId}/servicetypes/{id}")
    public Validation updateServiceType(@RequestBody ServiceType serviceType, @PathVariable Integer id,
            @PathVariable Integer companyId) {
        return service.updateServiceType(serviceType, id, companyId);
    }

    @RolesAllowed("ADMIN")
    @DeleteMapping("/companies/{companyId}/servicetypes/{serviceTypeId}")
    public Validation deleteServiceType(@PathVariable Integer companyId, @PathVariable Integer serviceTypeId) {
        return service.deleteServiceType(serviceTypeId);
    }
}