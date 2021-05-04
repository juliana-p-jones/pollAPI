package com.apress.service;

import com.apress.domain.Poll;
import com.apress.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PollService {
    //method logic goes here
   @Autowired
   private PollRepository pollRepository;

    public List<Poll> getAllPolls(){
        List<Poll> listOfPolls = new ArrayList<>();
      pollRepository.findAll().forEach(listOfPolls::add);
      return listOfPolls;
    }

    public void createPoll(Poll poll){
        pollRepository.save(poll);
    }

    public Optional<Poll> getPoll(Long id){
        return pollRepository.findById(id);
    }

    public void updatePoll(Poll poll){
        pollRepository.save(poll);
    }

    public void deletePoll(Long id){
        pollRepository.deleteById(id);
    }
}
