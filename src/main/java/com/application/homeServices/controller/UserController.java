package com.application.homeServices.controller;

import com.application.homeServices.dto.Customerdata;
import com.application.homeServices.dto.Req;
import com.application.homeServices.dto.Review;
import com.application.homeServices.service.ProfileServices;
import com.application.homeServices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class UserController {

    @Autowired
    private ProfileServices profileServices;
    @Autowired
    private UserService userService;

    @PostMapping("/data")
    public ResponseEntity<?> userData(@RequestBody Customerdata customerdata) {
        return ResponseEntity.ok(profileServices.CustomerEdit(customerdata));
    }

    @GetMapping("/find")
    public ResponseEntity<?> findWorker(@RequestBody Customerdata customerdata) {
        return ResponseEntity.ok(userService.findAllWorkers());
    }

    @PostMapping("/request")
    public ResponseEntity<?> create(@RequestBody Req req) {
        return ResponseEntity.ok(userService.create(req));
    }

    @GetMapping("/request/{id}")
    public ResponseEntity<?> allReq(@PathVariable long id) {
        return ResponseEntity.ok(userService.allReq(id));
    }

    @PostMapping ("/request/cancel")
    public ResponseEntity<?> cancelReq(@RequestBody long reqId) {
        return ResponseEntity.ok(userService.cancel(reqId));
    }

    @PostMapping ("/request/review/{reqId}")
    public ResponseEntity<?> cancelReq(@PathVariable Long reqId,@RequestBody Review review) {
        return ResponseEntity.ok(userService.reviwe(reqId,review));
    }
}