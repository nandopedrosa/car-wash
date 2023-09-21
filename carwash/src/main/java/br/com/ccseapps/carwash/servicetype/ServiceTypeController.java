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

@RestController
public class ServiceTypeController {

    @Autowired
    private ServiceTypeService service;

    @GetMapping("/servicetypes/{id}")
    public ServiceType getServiceType(@PathVariable Integer id) {
        return service.getServiceType(id);
    }

    @GetMapping("/companies/{companyId}/servicetypes")
    public List<ServiceType> getAllServiceTypesFromCompany(@PathVariable Integer companyId) {
        return service.getAllServiceTypesFromCompany(companyId);
    }

    @PostMapping("/companies/{companyId}/servicetypes")
    public Validation addServiceType(@RequestBody ServiceType serviceType, @PathVariable Integer companyId) {
        return service.addServiceType(serviceType, companyId);
    }

    @PutMapping("/companies/{companyId}/servicetypes/{id}")
    public Validation updateServiceType(@RequestBody ServiceType serviceType, @PathVariable Integer id,
            @PathVariable Integer companyId) {
        return service.updateServiceType(serviceType, id, companyId);
    }

    @DeleteMapping("/servicetypes/{id}")
    public Validation deleteServiceType(@PathVariable Integer id) {
        return service.deleteServiceType(id);
    }
}