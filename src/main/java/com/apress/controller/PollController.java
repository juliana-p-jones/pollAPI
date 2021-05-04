package com.apress.controller;

import com.apress.domain.Poll;
import com.apress.domain.Vote;
import com.apress.exception.ResourceNotFoundException;
import com.apress.repository.PollRepository;
import com.apress.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController // we need to identify our controllers with annotation restcontroller
public class PollController {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private PollService pollService;


    protected void verifyPoll(Long pollId) throws ResourceNotFoundException{
        Optional<Poll> poll = pollService.getPoll(pollId);
        if(!poll.isPresent()) {
            throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
        }
    }

    @RequestMapping(value = "/polls", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        List<Poll> p = pollService.getAllPolls();
        if(p.isEmpty()) {
            throw new ResourceNotFoundException("No poll created yet");}
        return new ResponseEntity<>(pollService.getAllPolls(), HttpStatus.OK);
    }

    @RequestMapping(value = "/polls", method = RequestMethod.POST)
    public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll) {
        pollService.createPoll(poll);
        // Set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(poll.getId())
                .toUri();
        responseHeaders.setLocation(newPollUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.GET)
    public ResponseEntity<?> getPoll(@PathVariable Long pollId){
       Optional<Poll> p = pollService.getPoll(pollId);
        if(!p.isPresent()) {
            throw new ResourceNotFoundException("Poll with id " + pollId + " not found");}
        return new ResponseEntity<> (p, HttpStatus.OK);
    }

@RequestMapping(value= "/polls/{pollId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId){
    verifyPoll(pollId);
    pollService.updatePoll(poll);
    return new ResponseEntity<>(HttpStatus.OK);
    }

@RequestMapping(value = "/polls/{pollId}", method = RequestMethod.DELETE)
public ResponseEntity<?> deletePoll(@PathVariable Long pollId){
    verifyPoll(pollId);
    pollService.deletePoll(pollId);
    return new ResponseEntity<>(HttpStatus.OK);
}


}
