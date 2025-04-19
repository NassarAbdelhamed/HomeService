package com.application.homeServices.repository;

import com.application.homeServices.models.WorkerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkerProfileRepo extends JpaRepository<WorkerProfile,Long> {
    Optional<WorkerProfile> findByUserId(long userId);
}
