package br.com.ccseapps.carwash.booking.dto;

import java.util.Objects;
import java.util.stream.Stream;

public class BookingDTO {
    private Integer branchId;
    private Integer serviceTypeId;
    private String datetime;

    public boolean hasNullMandatoryField() {
        return Stream.of(branchId, serviceTypeId, datetime)
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

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "BookingDTO [branchId=" + branchId + ", serviceTypeId=" + serviceTypeId + ", datetime=" + datetime + "]";
    }

}
