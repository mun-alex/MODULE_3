package kz.bitlab.m3_ch1.service.impl;

import kz.bitlab.m3_ch1.entities.UniUser;
import kz.bitlab.m3_ch1.repository.UniUserRepository;
import kz.bitlab.m3_ch1.service.UniUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UniUserServiceImpl implements UniUserService {

    @Autowired
    private UniUserRepository uniUserRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UniUser uniUser = uniUserRepository.findByEmail(s);

        if (uniUser != null) {
            User secUser = new User(uniUser.getEmail(), uniUser.getPassword(), uniUser.getRoles());
            return secUser;
        }

        return null;
    }

    @Override
    public UniUser getUserByEmail(String email) {
        return uniUserRepository.findByEmail(email);
    }
}
