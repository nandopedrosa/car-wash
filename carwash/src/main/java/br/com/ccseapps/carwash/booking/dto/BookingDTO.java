package br.com.ccseapps.carwash.booking.dto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BookingDTO {

    private Integer branchId;
    
    private Integer serviceTypeId;
    
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime datetime;
   
    private Integer  vehicleId;

    public boolean hasNullMandatoryField() {
        return Stream.of(branchId, serviceTypeId, datetime, vehicleId)
                .anyMatch(Objects::isNull);
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Integer getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "BookingDTO [branchId=" + branchId + ", serviceTypeId=" + serviceTypeId + ", datetime=" + datetime
                + ", vehicleId=" + vehicleId + "]";
    }
}
