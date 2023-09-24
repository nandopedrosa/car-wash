package br.com.ccseapps.carwash.schedule;

import java.time.DayOfWeek;
import java.util.Objects;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ccseapps.carwash.branch.Branch;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Column(nullable = false)
    private String openTime;

    @Column(nullable = false)
    private String closeTime;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    @JsonBackReference(value = "branch-schedule")
    private Branch branch;

    public boolean hasNullMandatoryField() {
        return Stream.of(dayOfWeek, openTime, closeTime)
                .anyMatch(Objects::isNull);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public int getOpenTimeHour() {
        return Integer.parseInt(this.openTime.split(":")[0]);
    }

    public int getOpenTimeMinute() {
        return Integer.parseInt(this.openTime.split(":")[1]);
    }

    public int getCloseTimeHour() {
        return Integer.parseInt(this.closeTime.split(":")[0]);
    }

    public int getCloseTimeMinute() {
        return Integer.parseInt(this.closeTime.split(":")[1]);
    }

}
