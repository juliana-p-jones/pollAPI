package com.apress.controller;

import com.apress.domain.Poll;
import com.apress.domain.Vote;
import com.apress.exception.ResourceNotFoundException;
import com.apress.repository.VoteRepository;
import com.apress.service.PollService;
import com.apress.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

@RestController
public class VoteController {
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private VoteService voteService;
    @Autowired
    private PollService pollService;

//    @RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.POST)
//    public ResponseEntity<?> createVote(@PathVariable Long pollId, @RequestBody Vote vote) {
//        vote = voteRepository.save(vote);
//        HttpHeaders responseHeaders = new HttpHeaders();
//        responseHeaders.setLocation(ServletUriComponentsBuilder.
//                fromCurrentRequest().path("/{id}").buildAndExpand(vote.getId()).toUri());
//        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
//    }
protected void verifyPoll(Long pollId) throws ResourceNotFoundException{
    Optional<Poll> poll = pollService.getPoll(pollId);
    if(!poll.isPresent()) {
        throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
    }
}

    protected void verifyVote(Long id) throws ResourceNotFoundException {
        Optional<Vote> vote = voteService.getVote(id);
        if(!vote.isPresent()) {
            throw new ResourceNotFoundException("Vote with id " + id + " not found");
        }
    }

    @RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.POST)
    public ResponseEntity<?> createVote(@PathVariable Long pollId, @RequestBody Vote
            vote) {
        verifyPoll(pollId);
        voteService.createVote(vote);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{pollId}").buildAndExpand(vote.getId()).toUri());

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value="/polls/{pollId}/votes", method=RequestMethod.GET)
    public ResponseEntity<?> getAllVotes(@PathVariable Long pollId) {
        verifyPoll(pollId);
        return new ResponseEntity<>(voteService.getAllVotes(pollId), HttpStatus.OK);
    }

//    @RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.GET)
//    public Iterable<Vote> getAllVotes(@PathVariable Long pollId) {
//        pollService.verifyPoll(pollId);
//        voteService.getAllVotes(pollId);
//        return voteRepository.findByPoll(pollId);
//    }

//    @RequestMapping(value = "/polls/{pollId}/votes/{voteId}", method = RequestMethod.DELETE)
//    public ResponseEntity<?> deleteVote(@PathVariable Long pollId, @PathVariable Long id){
//        verifyVote(id);
//        voteService.deleteVote(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//    @RequestMapping(value="/polls/{pollId}/votes", method=RequestMethod.GET)
//    public Iterable<Vote> getAllVotes(@PathVariable Long pollId) {
//        return voteRepository. findByPoll(pollId);
//    }
}
