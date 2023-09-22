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

@RestController
public class VehicleController {
    @Autowired
    private VehicleService service;

    @GetMapping("/users/vehicles")
    public List<Vehicle> getAllVehiclesFromCurrentUser(Principal principal) {
        return service.getAllVehiclesFromCurrentUser(principal);
    }

    @GetMapping("/users/vehicles/{id}")
    public Vehicle getVehicle(@PathVariable Integer id) { 
        return service.getVehicle(id);
    }

    @PostMapping("/users/vehicles")
    public Validation addVehicle(@RequestBody Vehicle v, Principal p) {
        return service.addVehicle(v, p);
    }

    @PutMapping("/users/vehicles/{id}")
    public Validation updateVehicle(@RequestBody Vehicle v, @PathVariable Integer id, Principal p) {
        return service.updateVehicle(v, id, p);
    }

    @DeleteMapping("/users/vehicles/{id}")
    public Validation deleteVehicle(@PathVariable Integer id) {
        return service.deleteVehicle(id);
    }
}
