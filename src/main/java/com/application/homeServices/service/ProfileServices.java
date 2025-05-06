package com.application.homeServices.service;


import com.application.homeServices.dto.Customerdata;
import com.application.homeServices.dto.MyDate;
import com.application.homeServices.dto.Role;
import com.application.homeServices.dto.Workerdata;
import com.application.homeServices.models.CustomerProfile;
import com.application.homeServices.models.User;
import com.application.homeServices.models.WorkerProfile;
import com.application.homeServices.repository.CustomerProfileRepo;
import com.application.homeServices.repository.UserRepo;
import com.application.homeServices.repository.WorkerProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServices {

    @Autowired
    private CustomerProfileRepo customerProfileRepo;
    @Autowired
   private UserRepo userRepo;

    @Autowired
    private WorkerProfileRepo workerProfileRepo;

    public MyDate profile(Long id){
        User user= userRepo.findById(id).get();
        if(user.getRole()== Role.USER){
            CustomerProfile customerProfile=customerProfileRepo.findByUserId(id).get();
            return new Customerdata(user.getId(), user.getEmail(), customerProfile.getName(),customerProfile.getPhoneNumber(),customerProfile.getAge());
        }
        else {
            WorkerProfile workerProfile=workerProfileRepo.findByUserId(id).get();
            return new Workerdata(
                    user.getId(),user.getEmail(),workerProfile.getName(),workerProfile.getJobTittle(),workerProfile.getAddress(),
                    workerProfile.getLatitude(), workerProfile.getLongitude(),workerProfile.getAge(),workerProfile.getAbout(),workerProfile.getPhoneNumber()
                    ,workerProfile.getSkills(),workerProfile.getCredentials());
        }
    }
    public CustomerProfile CustomerEdit(Customerdata customerdata){
        User user=userRepo.findById(customerdata.getUserId()).get();
            CustomerProfile customerProfile = customerProfileRepo.findByUserId(customerdata.getUserId()).get();
            customerProfile.setName(customerdata.getName());
            customerProfile.setAge(customerdata.getAge());
            customerProfile.setPhoneNumber(customerdata.getPhoneNumber());
            return customerProfileRepo.save(customerProfile);
    }
    public WorkerProfile WorkerEdit(Workerdata workerdata){
        User user=userRepo.findById(workerdata.getWorkerId()).get();
            WorkerProfile workerProfile = workerProfileRepo.findByUserId(workerdata.getWorkerId()).get();
            workerProfile.setName(workerdata.getName());
            workerProfile.setJobTittle(workerdata.getJobTittle());
            workerProfile.setAddress(workerdata.getAddress());
            workerProfile.setLatitude(workerdata.getLatitude());
            workerProfile.setLongitude(workerdata.getLongitude());
            workerProfile.setAge(workerdata.getAge());
            workerProfile.setAbout(workerdata.getAbout());
            workerProfile.setPhoneNumber(workerdata.getPhoneNumber());
            workerProfile.setSkills(workerdata.getSkills());
            return workerProfileRepo.save(workerProfile);
    }
}
