package com.vnptit.vnpthis.security;

import com.vnptit.vnpthis.domain.cdt.AdmUser;
import com.vnptit.vnpthis.repository.cdt.AdmUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import java.util.List;

import static java.util.Arrays.asList;

@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired private AdmUserRepository admUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return admUserRepository.getByUserName(username)
            .map(this::createUserDetail)
            .orElseThrow(() -> new UsernameNotFoundException("User " + username + " was not found in the database"));
    }

    private UserDetails createUserDetail(AdmUser admUser) {
        List<GrantedAuthority> grantedAuthorities = asList(new SimpleGrantedAuthority(AuthoritiesConstants.ADMIN));
        return new User(admUser.getUserName(), admUser.getUserPwd(), grantedAuthorities);
    }
}

