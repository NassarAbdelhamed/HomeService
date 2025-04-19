package com.application.homeServices.service;


import com.application.homeServices.dto.Req;
import com.application.homeServices.dto.Role;
import com.application.homeServices.dto.Status;
import com.application.homeServices.dto.Workerdata;
import com.application.homeServices.models.Request;
import com.application.homeServices.models.User;
import com.application.homeServices.models.WorkerProfile;
import com.application.homeServices.repository.RequestRepo;
import com.application.homeServices.repository.UserRepo;
import com.application.homeServices.repository.WorkerProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Workerdata> findAllWorkers(){
        List<User> list= userRepo.findByRole(Role.WORKER);
        List<Workerdata> l =new ArrayList<Workerdata>();
        for(User u :list){
           WorkerProfile w= workerProfileRepo.findByUserId(u.getId()).get();
            Workerdata workerdata=new Workerdata(w.getUserId(),u.getEmail(),w.getName(),w.getJobTittle(),w.getAddress(),w.getLatitude(),w.getLongitude());
            l.add(workerdata);
        }
        return l;
    }

    public List<Request> allReq(Long id){
        return requestRepo.findByUserId(id);
    }
    public Request create(Req req){
        Request request=new Request();
        request.setUserId(req.getUserId());
        request.setWorkerId(req.getWorkerId());
        request.setAddress(req.getAddress());
        request.setDescription(req.getDescription());
        request.setLatitude(req.getLatitude());
        request.setLongitude(req.getLongitude());
        request.setStatus(Status.waiting);
        request.setRate(0);
        request.setComment("");
        return requestRepo.save(request);
    }

}
