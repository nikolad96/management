package com.example.management.model.audit;

import com.example.management.model.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().describeConstable();
    }

}