package br.com.ccseapps.carwash.branch;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.ccseapps.carwash.booking.Booking;
import br.com.ccseapps.carwash.company.Company;
import br.com.ccseapps.carwash.schedule.Schedule;
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

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "branch-schedule")
    private List<Schedule> schedules;

    // see
    // http://bartoszkomin.blogspot.com/2017/01/many-to-many-relation-with-hibernate.html
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "branch_service_type", joinColumns = @JoinColumn(name = "branch_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "service_type_id", referencedColumnName = "id"), uniqueConstraints = {
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

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
    
    // Auxiliary method to return a given schedule by day of the week
    private Schedule getScheduleByDayOfTheWeek(DayOfWeek dow) {
        for (Schedule s : this.schedules) {
            if (s.getDayOfWeek() == dow) {
                return s;
            }
        }
        return null;
    }

    /**
     * Returns the timetable of all schedules by service type for a given date
     * 
     * @param serviceType the desired service type with a given duration, which
     *                    affects the time table returned
     * @param targetDate  the desired date to be schedule with a given day of the
     *                    week, which affects the time table returned
     * @return a list of all schedules for a given day for a given service type
     */
    public List<LocalDateTime> getAllSchedulesForServiceType(ServiceType serviceType, LocalDate targetDate) {
        List<LocalDateTime> res = new ArrayList<>();

        // Let's find out which schedule we have to consider by day of the week
        DayOfWeek targetDayOfWeek = targetDate.getDayOfWeek();
        Schedule schedule = this.getScheduleByDayOfTheWeek(targetDayOfWeek);
        if (schedule == null) {
            return res; // no schedule for the day of the week
        }
        
        // Starting from Open until Close, we add available slots incrementing by the service type duration
        LocalDateTime open = LocalDateTime.of(targetDate.getYear(), targetDate.getMonth(), targetDate.getDayOfMonth(),
                schedule.getOpenTimeHour(), schedule.getOpenTimeMinute());
                
        LocalDateTime close = LocalDateTime.of(targetDate.getYear(), targetDate.getMonth(), targetDate.getDayOfMonth(),
                schedule.getCloseTimeHour(), schedule.getCloseTimeMinute());

        LocalDateTime current = open;

        while(!current.equals(close)) {
            res.add(current);
            current = current.plusMinutes(serviceType.getDuration());
        }
        return res;
    }

    public boolean isDatetimeAvailableForServiceType(ServiceType serviceType, LocalDateTime datetime) {
        for(Booking b : bookings) {
            if(b.getServiceType().equals(serviceType) && b.getDatetime().equals(datetime)){
                return false;
            }
        }
        return true;
    }
}
