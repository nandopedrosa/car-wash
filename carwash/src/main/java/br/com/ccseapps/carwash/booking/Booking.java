package br.com.ccseapps.carwash.booking;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ccseapps.carwash.branch.Branch;
import br.com.ccseapps.carwash.servicetype.ServiceType;
import br.com.ccseapps.carwash.user.User;
import br.com.ccseapps.carwash.vehicle.Vehicle;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime datetime;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    @JsonBackReference(value = "vehicle-booking")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    @JsonBackReference(value = "branch-booking")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference(value = "user-booking")
    private User user;

    @ManyToOne
    @JoinColumn(name = "service_type_id", nullable = false)
    @JsonBackReference(value = "service-type-booking")
    private ServiceType serviceType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }
}
