package com.motorlog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.util.Assert;

public class SecurityContextFactory implements WithSecurityContextFactory<WithMockUserAccount> {

    @Autowired
    private UserAccountService userAccountService;

    public SecurityContext createSecurityContext(WithMockUserAccount user) {
        String username = user.username();
        Assert.hasLength(username, "The username must be non-empty String");
        UserDetails principal = userAccountService.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        return context;
    }
}