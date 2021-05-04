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

protected void verifyPoll(Long pollId) throws ResourceNotFoundException{
    Optional<Poll> poll = pollService.getPoll(pollId);
    if(!poll.isPresent()) {
        throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
    }
}

    protected void verifyVote(Long voteId) throws ResourceNotFoundException {
        Optional<Vote> vote = voteService.getVote(voteId);
        if(!vote.isPresent()) {
            throw new ResourceNotFoundException("Vote with id " + voteId + " not found");
        }
    }

    @RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.POST)
    public ResponseEntity<?> createVote(@PathVariable Long pollId, @RequestBody Vote
            vote) {
        verifyPoll(pollId);
        voteService.createVote(vote, pollId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value="/polls/{pollId}/votes", method=RequestMethod.GET)
    public ResponseEntity<?> getAllVotes(@PathVariable Long pollId) {
        verifyPoll(pollId);
        return new ResponseEntity<>(voteService.getAllVotes(pollId), HttpStatus.OK);
    }

    @RequestMapping(value = "/polls/votes/{voteId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteVote(@PathVariable Long voteId){
     verifyVote(voteId);
        voteService.deleteVote(voteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
