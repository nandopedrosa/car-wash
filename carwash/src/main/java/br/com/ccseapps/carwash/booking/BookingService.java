package br.com.ccseapps.carwash.booking;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ccseapps.carwash.booking.dto.BookingDTO;
import br.com.ccseapps.carwash.branch.Branch;
import br.com.ccseapps.carwash.branch.BranchService;
import br.com.ccseapps.carwash.servicetype.ServiceType;
import br.com.ccseapps.carwash.servicetype.ServiceTypeService;
import br.com.ccseapps.carwash.user.User;
import br.com.ccseapps.carwash.user.UserRepository;
import br.com.ccseapps.carwash.util.Status;
import br.com.ccseapps.carwash.util.Validation;
import br.com.ccseapps.carwash.vehicle.Vehicle;

@Service
public class BookingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private BranchService branchService;

    @Autowired
    private ServiceTypeService serviceTypeService;

    public Validation addBooking(BookingDTO bookingDto, Principal principal) {
        if (bookingDto.hasNullMandatoryField()) {
            return new Validation("Please, fill the mandatory fields.", Status.ERROR);
        }

        // Let's check if the time is available for the given date
        Branch branch = branchService.getBranch(bookingDto.getBranchId());
        ServiceType serviceType = serviceTypeService.getServiceType(bookingDto.getServiceTypeId());

        if(!branch.isDatetimeAvailableForServiceType(serviceType, bookingDto.getDatetime())) {
             return new Validation("Date/time not available", Status.ERROR);
        }        
        
        Booking booking = new Booking();

        User user = userRepository.findByEmail(principal.getName()).orElse(null);
        booking.setUser(user);
        
        booking.setBranch(branch);
        
        booking.setServiceType(serviceType);

        Vehicle vehicle = new Vehicle();
        vehicle.setId(bookingDto.getVehicleId());
        booking.setVehicle(vehicle);

        booking.setDatetime(bookingDto.getDatetime());
    
        bookingRepo.save(booking);

        return new Validation("Booking confirmed", Status.OK);
    }

}
