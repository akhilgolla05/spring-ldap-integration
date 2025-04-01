package com.example.ad_integration.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomLdapAuthoritiesPopulator implements LdapAuthoritiesPopulator {

    private final LdapTemplate ldapTemplate;

    @Override
    public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData, String username) {

        List<GrantedAuthority> authorities = new ArrayList<>();

        try {
            // Search for user groups in LDAP
             userData.getStringAttribute("gidNumber");
             String type = userData.getStringAttribute("employeeType");
            System.out.println(userData.getStringAttribute("employeeType"));
//            List<String> groupNames = ldapTemplate.search(
//                    "ou=users,ou=srs,dc=srs,dc=com", // 🔹 Base DN for group search
//                    "(member=cn=" + username + ",ou=users,ou=srs,dc=srs,dc=com)", // 🔹 LDAP filter
//                    (AttributesMapper<String>) attrs -> attrs.get("cn").get().toString()
//            );

            // Convert LDAP groups to Spring Security roles

            authorities.add(new SimpleGrantedAuthority("ROLE_" + type)); // 🔹 Format as ROLE_GROUPNAME

        } catch (Exception e) {
            e.printStackTrace();
        }

        return authorities;
    }
}
