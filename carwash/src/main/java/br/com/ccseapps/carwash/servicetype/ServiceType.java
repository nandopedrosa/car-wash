package br.com.ccseapps.carwash.servicetype;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.ccseapps.carwash.booking.Booking;
import br.com.ccseapps.carwash.branch.Branch;
import br.com.ccseapps.carwash.company.Company;
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
public class ServiceType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer duration; // in minutes

    @OneToMany(mappedBy = "serviceType", cascade = CascadeType.REMOVE)
    @JsonManagedReference(value = "service-type-booking")
    private List<Booking> bookings;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "branch_service_type", joinColumns = @JoinColumn(name = "service_type_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "branch_id", referencedColumnName = "id"), uniqueConstraints = {
            @UniqueConstraint(columnNames = { "service_type_id", "branch_id" }) })
    @JsonIgnoreProperties("serviceTypes")
    private List<Branch> branches;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    @JsonBackReference(value = "company-servicetypes")
    private Company company;

    public boolean hasNullMandatoryField() {
        return Stream.of(name, price, company)
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public void addBranch(Branch branch) {
        this.branches.add(branch);
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

}
