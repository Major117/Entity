package fr.laposte.entity.security.services;

import fr.laposte.entity.model.User;
import fr.laposte.entity.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String log) throws UsernameNotFoundException {

        User user = new User();

        if (log.trim().isEmpty()) {
            throw new UsernameNotFoundException("Le login est vide");
        }

        if (userRepository.findById(log).isPresent()) {
            user = userRepository.findById(log).get();
        }  else {
            throw new UsernameNotFoundException("Le login " + log + " n'existe pas.");
        }
        return UserDetailsImpl.build(user);
    }

}
