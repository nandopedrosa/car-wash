package br.com.ccseapps.carwash.vehicle;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {
    public List<Vehicle> findByUserId(Integer userId);
}