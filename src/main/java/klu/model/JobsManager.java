package klu.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import klu.repository.JobRepository; 

@Service
public class JobsManager {

    @Autowired
    private JobRepository JR;

    public String createJob(Jobs job) {
        try {
        	JR.save(job);  // Save the job to the repository
            return "Job created successfully";  // Return success message
        } catch (Exception e) {
            // Catch any exceptions and return a failure message
            return "Error creating job: " + e.getMessage();
        }
    }
    
}
