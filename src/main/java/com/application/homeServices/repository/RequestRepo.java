package com.application.homeServices.repository;

import com.application.homeServices.dto.Status;
import com.application.homeServices.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RequestRepo extends JpaRepository<Request, Long> {
    List<Request> findByUserId(long userId);

    List<Request> findByWorkerId(long workerId);

    List<Request> findByStatus(Status status);
}