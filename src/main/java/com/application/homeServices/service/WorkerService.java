package com.application.homeServices.service;


import com.application.homeServices.models.Request;
import com.application.homeServices.repository.RequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {

    @Autowired
    private RequestRepo requestRepo;


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
}
