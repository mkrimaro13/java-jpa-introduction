package co.com.marimaro.pizzeria.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.com.marimaro.pizzeria.persistance.entity.UserEntity;
import co.com.marimaro.pizzeria.persistance.entity.UserRole;
import co.com.marimaro.pizzeria.persistance.repository.UserRepository;

@Service
public class UserSecurityService implements UserDetailsService {

        @Autowired
        private UserRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                UserEntity user = userRepository.findById(username)
                                .orElseThrow(() -> new UsernameNotFoundException(
                                                "El usuario " + username + "no ha sido encontrado"));

                String[] roles = user.getRoles()
                                .stream().map(UserRole::getRole)
                                .toArray(String[]::new);

                return User.builder()
                                .username(user.getUsername()).password(user.getPassword())
                                // .roles(roles)
                                .authorities(grantedAuthorities(roles))
                                .accountLocked(user.getLocked())
                                .disabled(user.getDisabled())
                                .build();
        }

        private String[] getAuthorities(String role) {
                if ("ADMIN".equals(role) || "CUSTOMER".equals(role)) {
                        return new String[] { "random_order" };
                }
                return new String[] {};
        }

        private List<GrantedAuthority> grantedAuthorities(String[] roles) {
                List<GrantedAuthority> authorities = new ArrayList<>(roles.length);
                for (String role : roles) {
                        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
                        for (String authority : this.getAuthorities(role)) {
                                authorities.add(new SimpleGrantedAuthority(authority));
                        }
                }
                return authorities;
        }
}
