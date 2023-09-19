package br.com.ccseapps.carwash.user;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ccseapps.carwash.util.Status;
import br.com.ccseapps.carwash.util.Validation;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public Validation register(User user) {
        if (user.hasNullMandatoryField()) {
            return new Validation("Please, fill the mandatory fields.", Status.ERROR);
        }

        if (user.getPassword().length() < 6) {
            return new Validation("Password must be at least 6 characters long.", Status.ERROR);
        }

        // Generating the user's password hash with SHA-256 algorithm
        user.setPasswordHash(DigestUtils.sha256Hex(user.getPassword()));

        try {
            repo.save(user);
        } catch (Exception e) {
            return new Validation("There was an error while accessing the database. Details:\n\n" + e.toString(),
                    Status.ERROR);
        }

        return new Validation("User succesfully registered.", Status.OK);
    }
}
