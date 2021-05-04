package com.apress.service;

import com.apress.domain.Poll;
import com.apress.domain.Vote;
import com.apress.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VoteService {
    @Autowired
    private VoteRepository voteRepository;

    public void createVote(Vote vote){
        voteRepository.save(vote);
    }

    public List<Vote> getAllVotes(Long pollId){
       return (List<Vote>)voteRepository.findAll();

    }

    public Optional<Vote> getVote(Long id) {
        return voteRepository.findById(id);
    }

//    public void deleteVote(Long id) {
//        voteRepository.deleteById(id);
//    }
}
