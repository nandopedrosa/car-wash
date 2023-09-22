package br.com.ccseapps.carwash.vehicle;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ccseapps.carwash.user.User;
import br.com.ccseapps.carwash.user.UserRepository;
import br.com.ccseapps.carwash.util.Status;
import br.com.ccseapps.carwash.util.Validation;

@Service
public class VehicleService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private VehicleRepository repo;

    private void setCurrentUserAsVehicleOwner(Vehicle vehicle, Principal principal) {
        User currentUser = userRepository.findByEmail(principal.getName()).orElse(null);
        vehicle.setUser(currentUser);
    }

    public Validation addVehicle(Vehicle vehicle, Principal principal) {
        if (vehicle.hasNullMandatoryField()) {
            return new Validation("Please, fill the mandatory fields.", Status.ERROR);
        }
        this.setCurrentUserAsVehicleOwner(vehicle, principal);
        repo.save(vehicle);
        return new Validation("Vehicle successfully added.", Status.OK);
    }

    public Validation updateVehicle(Vehicle vehicle, Integer id, Principal principal) {
        if (vehicle.hasNullMandatoryField()) {
            return new Validation("Please, fill the mandatory fields.", Status.ERROR);
        }
        vehicle.setId(id);
        this.setCurrentUserAsVehicleOwner(vehicle, principal);
        repo.save(vehicle);
        return new Validation("Vehicle successfully updated.", Status.OK);
    }

    public List<Vehicle> getAllVehiclesFromCurrentUser(Principal principal) {
        List<Vehicle> vehicles = new ArrayList<>();
        User currentUser = userRepository.findByEmail(principal.getName()).orElse(null);
        repo.findByUserId(currentUser.getId()).forEach(vehicles::add);
        return vehicles;
    }

    public Vehicle getVehicle(Integer id) {
        Vehicle v = repo.findById(id).orElse(null);
        return v;
    }

    public Validation deleteVehicle(Integer id) {
        repo.deleteById(id);
        return new Validation("Vehicle successfully deleted.", Status.OK);
    }

}
