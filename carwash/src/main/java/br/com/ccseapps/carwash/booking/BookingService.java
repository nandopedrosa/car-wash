package br.com.ccseapps.carwash.booking;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ccseapps.carwash.booking.dto.BookingDTO;
import br.com.ccseapps.carwash.branch.Branch;
import br.com.ccseapps.carwash.servicetype.ServiceType;
import br.com.ccseapps.carwash.user.User;
import br.com.ccseapps.carwash.user.UserRepository;
import br.com.ccseapps.carwash.util.Status;
import br.com.ccseapps.carwash.util.Validation;

@Service
public class BookingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository repo;

    public Validation addBooking(BookingDTO bookingDto, Principal principal) {
        if (bookingDto.hasNullMandatoryField()) {
            return new Validation("Please, fill the mandatory fields.", Status.ERROR);
        }

        User user = userRepository.findByEmail(principal.getName()).orElse(null);
        Branch branch = new Branch();
        branch.setId(bookingDto.getBranchId());
        ServiceType serviceType = new ServiceType();
        serviceType.setId(bookingDto.getServiceTypeId());

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setBranch(branch);
        booking.setServiceType(serviceType);
        booking.setDatetime(bookingDto.getDatetime());

        /*
         * Check booking availability, considering:
         * - servicetype duration
         * - previous bookings
         * - start time)
         */
        repo.save(booking);

        return new Validation("Booking confirmed", Status.OK);
    }

}
