package com.application.homeServices.service;


import com.application.homeServices.dto.*;
import com.application.homeServices.models.Request;
import com.application.homeServices.models.User;
import com.application.homeServices.models.WorkerProfile;
import com.application.homeServices.repository.RequestRepo;
import com.application.homeServices.repository.UserRepo;
import com.application.homeServices.repository.WorkerProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private WorkerProfileRepo workerProfileRepo;

    @Autowired
    private RequestRepo requestRepo;

    @Autowired
    private JavaMailSender javaMailSender;

    public List<Workerdata> findAllWorkers(){
        List<User> list= userRepo.findByRole(Role.WORKER);
        List<Workerdata> l =new ArrayList<Workerdata>();
        for(User u :list){
           WorkerProfile w= workerProfileRepo.findByUserId(u.getId()).get();
            Workerdata workerdata=new Workerdata(w.getUserId(),u.getEmail(),w.getName(),w.getJobTittle(),w.getAddress(),w.getLatitude(),w.getLongitude(),w.getAge(),w.getPhoneNumber(),w.getSkills(),w.getCredentials());
            l.add(workerdata);
        }
        return l;
    }

    public List<Request> allReq(Long id){
        return requestRepo.findByUserId(id);
    }
    public Request create(Req req){
        String name = userRepo.findById(req.getUserId()).get().getName();
        String email=userRepo.findById(req.getWorkerId()).get().getEmail();
        Request request=new Request();
        request.setUserId(req.getUserId());
        request.setWorkerId(req.getWorkerId());
        request.setAddress(req.getAddress());
        request.setDescription(req.getDescription());
        request.setSalary(req.getSalary());
        request.setLatitude(req.getLatitude());
        request.setLongitude(req.getLongitude());
        request.setStatus(Status.waiting);
        request.setRate(0);
        request.setComment("");
        sendMassage (email,name);
        return requestRepo.save(request);
    }

    public void sendMassage(String email,String name){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Sala7ly@gmail.com");
        message.setTo(email);
        message.setSubject("You have a  new request");
        message.setText(name+ "sent you a request and  waiting for your reply");
        javaMailSender.send(message);
    }

    public Request cancel(Long reqId){
        Request request=requestRepo.findById(reqId).get();
        request.setStatus(Status.canceled);
        return requestRepo.save(request);

    }
    public Request reviwe(Long reqId, Review reviwe){
        Request request=requestRepo.findById(reqId).get();
        if (request.getStatus() != Status.accept){throw new RuntimeException();}
        request.setRate(reviwe.getRate());
        request.setComment(reviwe.getComment());
        return requestRepo.save(request);
    }
}
