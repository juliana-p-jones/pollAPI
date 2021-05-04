package com.apress.controller;

import com.apress.domain.Poll;
import com.apress.domain.Vote;
import com.apress.dto.VoteResult;
import com.apress.exception.ResourceNotFoundException;
import com.apress.repository.PollRepository;
import com.apress.repository.VoteRepository;
import com.apress.service.ComputeResultService;
import com.apress.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ComputeResultController {
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private PollRepository pollRepository;
    @Autowired
    private PollService pollService;
    @Autowired
    private ComputeResultService computeResultService;

    @RequestMapping(value = "/computeresult", method = RequestMethod.GET)
    public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
        Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);
        return new ResponseEntity<VoteResult>(computeResultService.computeResult(pollId), HttpStatus.OK);
    }



//    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.GET)
//    public ResponseEntity<?> getPoll(@PathVariable Long pollId){
//        Optional<Poll> p = pollService.getPoll(pollId);
//        if(!p.isPresent()) {
//            throw new ResourceNotFoundException("Poll with id " + pollId + " not found");}
//        return new ResponseEntity<> (p, HttpStatus.OK);
//    }
}

