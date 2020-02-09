package com.app.sso.service;

import com.app.sso.bean.User;
import com.app.sso.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.app.sso.service.UserService.SERVICE_NAME;

@Service(SERVICE_NAME)
public class UserServiceImp implements UserService {

    private static final Logger log = LogManager.getLogger(UserServiceImp.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<User> listAll() {
        Iterable<User> userIterable = userRepository.findAll();
        return StreamSupport.stream(userIterable.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        log.info("loadUserByUsername() : {}", username);
        return user;
    }

    @Override
    public UserDetails loadUserBySAML(SAMLCredential samlCredential) throws UsernameNotFoundException {
        Assert.notNull(samlCredential, "Saml credential is required");
        User user = userRepository.findByEmail(samlCredential.getNameID().getValue());
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        log.info("loadUserBySAML() : {}", samlCredential.getNameID().getValue());
        return user;
    }

    @Override
    public Optional<User> getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return findByUsername(currentUserName);
        }
        return Optional.empty();
    }
}
