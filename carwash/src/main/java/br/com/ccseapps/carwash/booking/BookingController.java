package br.com.ccseapps.carwash.booking;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ccseapps.carwash.booking.dto.BookingDTO;
import br.com.ccseapps.carwash.util.Validation;
import jakarta.annotation.security.RolesAllowed;

@RestController
public class BookingController {

    @Autowired
    private BookingService service;
    
    @RolesAllowed({"ADMIN", "CUSTOMER"})
    @PostMapping("/users/bookings")
    public Validation addBooking(@RequestBody BookingDTO bookingDto, Principal principal) {
        return service.addBooking(bookingDto, principal);
    }
    
}
