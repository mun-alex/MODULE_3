package kz.bitlab.m3_ch1.service;

import kz.bitlab.m3_ch1.entities.UniUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UniUserService extends UserDetailsService {
    UniUser getUserByEmail(String email);
    UniUser createUser(UniUser newUser);
}
