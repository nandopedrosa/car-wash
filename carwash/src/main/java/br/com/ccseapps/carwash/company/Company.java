package br.com.ccseapps.carwash.company;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.ccseapps.carwash.branch.Branch;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Branch> branches;

    public boolean hasNullMandatoryField() {
        return Stream.of(name)
                .anyMatch(Objects::isNull);
    }

    public Company(Integer id) {
        this.id = id;
    }

    public Company() {

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

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

}
