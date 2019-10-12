package net.paragon.dashfaces.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.paragon.dashfaces.domain.entity.UserProfile;
import net.paragon.dashfaces.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<UserProfile> user = userRepository.findByUsername(s);

        if (user.isPresent()){
            return user.get();
        }else{
            throw new UsernameNotFoundException(String.format("Username[%s] not found"));
        }
    }
}
