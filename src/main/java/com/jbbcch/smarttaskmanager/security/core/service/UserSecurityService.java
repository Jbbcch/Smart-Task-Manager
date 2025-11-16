package com.jbbcch.smarttaskmanager.security.core.service;

import com.jbbcch.smarttaskmanager.security.core.model.entity.UserSecurity;
import com.jbbcch.smarttaskmanager.security.core.repository.UserSecurityRepository;
import com.jbbcch.smarttaskmanager.security.shared.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private final AuthorityRepository authorityRepository;
    private final UserSecurityRepository userSecurityRepository;

    @Override
    public UserSecurity loadUserByUsername(String username) {
        UserSecurity loadedUser = userSecurityRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<GrantedAuthority> loadedAuthorities = authorityRepository.findDistinctAuthoritiesByUserId(loadedUser.getId())
                .stream().map(authority -> new SimpleGrantedAuthority(authority.toString()))
                .collect(Collectors.toList());
        loadedUser.setAuthorities(loadedAuthorities);
        return loadedUser;
    }
}
