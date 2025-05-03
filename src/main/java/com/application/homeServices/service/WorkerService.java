package com.application.homeServices.service;


import com.application.homeServices.dto.Review2;
import com.application.homeServices.dto.Review;
import com.application.homeServices.dto.Status;
import com.application.homeServices.models.Request;
import com.application.homeServices.repository.RequestRepo;
import com.application.homeServices.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkerService {

    @Autowired
    private RequestRepo requestRepo;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JavaMailSender javaMailSender;


    public List<Request> allReq(Long id){
        return requestRepo.findByWorkerId(id);
    }


    @Cacheable(value = "rate", key = "#id")
    public double rate(Long id)  {
      List<Request> requests =requestRepo.findByWorkerId(id);
      if(requests.size()==0){return 0;}
      double sum=0;
      for(Request r:requests){
          sum+=r.getRate();
      }
      return sum/requests.size();
    }

    public List<Review2> Comments(Long id)  {
        List<Request> requests =requestRepo.findByWorkerId(id);
        List<Review2> comments=new ArrayList<>();
        for(Request r:requests){
          if(r.getComment()!=""){
              String name= userRepo.findById(r.getUserId()).get().getName();
              comments.add(new Review2(r.getUserId(),name,r.getRate(),r.getComment()));
          }
        }
        return comments;
    }
    public Request accept(Long reqId){
        Request request=requestRepo.findById(reqId).get();
        String name=userRepo.findById(request.getUserId()).get().getName();
        String email=userRepo.findById(request.getUserId()).get().getEmail();
        request.setStatus(Status.accept);
        sendacceptMassage(name,email);
        return requestRepo.save(request);

    }
    public Request cancel(Long reqId){
        Request request=requestRepo.findById(reqId).get();
        String name=userRepo.findById(request.getUserId()).get().getName();
        String email=userRepo.findById(request.getUserId()).get().getEmail();
        request.setStatus(Status.canceled);
        sendCanceltMassage(name,email);
        return requestRepo.save(request);

    }

    public void sendacceptMassage(String email,String name){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Sala7ly@gmail.com");
        message.setTo(email);
        message.setSubject("You have a  new request");
        message.setText(name+ "agree with you");
        javaMailSender.send(message);
    }

    public void sendCanceltMassage(String email,String name){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Sala7ly@gmail.com");
        message.setTo(email);
        message.setSubject("You have a  new request");
        message.setText(name+ " rejected you");
        javaMailSender.send(message);
    }
}
