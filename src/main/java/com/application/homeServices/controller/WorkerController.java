package com.application.homeServices.controller;

import com.application.homeServices.dto.Customerdata;
import com.application.homeServices.dto.Workerdata;
import com.application.homeServices.service.ImageService;
import com.application.homeServices.service.ProfileServices;
import com.application.homeServices.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    private ProfileServices profileServices;

    @Autowired
    private ImageService imageService;

    @Autowired
    private WorkerService workerService;

    @PostMapping("/data")
    public ResponseEntity<?> userData(@RequestBody Workerdata workerdata) {
        return ResponseEntity.ok(profileServices.WorkerEdit(workerdata));
    }

    @GetMapping("/request/{id}")
    public ResponseEntity<?> allReq(@PathVariable Long id) {
        return ResponseEntity.ok(workerService.allReq(id));
    }

    @GetMapping("/rate/{id}")
    public ResponseEntity<?> rateq(@PathVariable Long id){
        return ResponseEntity.ok(workerService.rate(id));
    }


    @PostMapping("/credentials/upload/{userId}")
    public ResponseEntity<String> uploadImage(@PathVariable Long userId, @RequestParam("file") MultipartFile file) {
        try {
            imageService.saveCredentials(userId, file);
            return ResponseEntity.ok("Image uploaded successfully!");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Failed to upload image: " + e.getMessage());
        }
    }

    @GetMapping("/credentials/{userId}")
    public ResponseEntity<Resource> getImage(@PathVariable Long userId) {
        try {
            Resource resource = imageService.getCredentials(userId);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // or MediaType.IMAGE_PNG depending on your image type
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @PostMapping ("/request/cancel")
    public ResponseEntity<?> cancelReq(@RequestBody long reqId) {
        return ResponseEntity.ok(workerService.cancel(reqId));
    }

    @PostMapping ("/request/accept")
    public ResponseEntity<?> acceptReq (@RequestBody long reqId) {
        return ResponseEntity.ok(workerService.accept(reqId));
    }
    @GetMapping("/comments/{id}")
    public ResponseEntity<?> comment(@PathVariable Long id){
        return ResponseEntity.ok(workerService.Comments(id));
    }
}