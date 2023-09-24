package br.com.ccseapps.carwash.branch;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.ccseapps.carwash.booking.Booking;
import br.com.ccseapps.carwash.company.Company;
import br.com.ccseapps.carwash.servicetype.ServiceType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.UniqueConstraint;

@Entity
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    @JsonBackReference(value = "company-branch")
    private Company company;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.REMOVE)
    @JsonManagedReference(value = "branch-booking")
    private List<Booking> bookings;
    
    //see http://bartoszkomin.blogspot.com/2017/01/many-to-many-relation-with-hibernate.html
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "branch_service_type", 
    joinColumns = @JoinColumn(name = "branch_id", referencedColumnName = "id"), 
    inverseJoinColumns = @JoinColumn(name = "service_type_id", referencedColumnName = "id"), 
    uniqueConstraints = {
            @UniqueConstraint(columnNames = { "branch_id", "service_type_id" })
         })
    @JsonIgnoreProperties("branches")
    private List<ServiceType> serviceTypes;

    public boolean hasNullMandatoryField() {
        return Stream.of(name, company)
                .anyMatch(Objects::isNull);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<ServiceType> getServiceTypes() {
        return serviceTypes;
    }

    public void setServiceTypes(List<ServiceType> serviceTypes) {
        this.serviceTypes = serviceTypes;
    }

    public void addServiceType(ServiceType serviceType) {
        this.serviceTypes.add(serviceType);
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
