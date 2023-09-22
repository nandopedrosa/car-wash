package br.com.ccseapps.carwash.vehicle;

import java.security.Principal;
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
public class VehicleController {
    @Autowired
    private VehicleService service;

    @RolesAllowed({"ADMIN", "CUSTOMER"})
    @GetMapping("/users/vehicles")
    public List<Vehicle> getAllVehiclesFromCurrentUser(Principal principal) {
        return service.getAllVehiclesFromCurrentUser(principal);
    }

    @RolesAllowed({"ADMIN", "CUSTOMER"})
    @GetMapping("/users/vehicles/{id}")
    public Vehicle getVehicle(@PathVariable Integer id) { 
        return service.getVehicle(id);
    }

    @RolesAllowed({"ADMIN", "CUSTOMER"})
    @PostMapping("/users/vehicles")
    public Validation addVehicle(@RequestBody Vehicle vehicle, Principal principal) {
        return service.addVehicle(vehicle, principal);
    }

    @RolesAllowed({"ADMIN", "CUSTOMER"})
    @PutMapping("/users/vehicles/{id}")
    public Validation updateVehicle(@RequestBody Vehicle vehicle, @PathVariable Integer vehicleId, Principal principal) {
        return service.updateVehicle(vehicle, vehicleId, principal);
    }

    @RolesAllowed({"ADMIN", "CUSTOMER"})
    @DeleteMapping("/users/vehicles/{id}")
    public Validation deleteVehicle(@PathVariable Integer id) {
        return service.deleteVehicle(id);
    }
}
