package br.com.ccseapps.carwash.booking;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Integer> {
    public List<Booking> findByUserId(Integer userId);

    public List<Booking> findByServiceTypeId(Integer serviceTypeId);

    public List<Booking> findByBranchId(Integer branchId);
}