package com.application.homeServices.repository;

import com.application.homeServices.models.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerProfileRepo extends JpaRepository<CustomerProfile,Long> {
    Optional<CustomerProfile> findByUserId(long userId);
}
