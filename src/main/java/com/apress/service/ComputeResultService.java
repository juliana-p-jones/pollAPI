package com.apress.service;

import com.apress.domain.Poll;
import com.apress.domain.Vote;
import com.apress.dto.OptionCount;
import com.apress.dto.VoteResult;
import com.apress.repository.PollRepository;
import com.apress.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ComputeResultService {
    @Autowired
    VoteRepository voteRepository;
    public VoteResult computeResult (Long pollId) {
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);
        //algorithm to count votes
        int totalVotes = 0;
        Map<Long, OptionCount> tempMap = new HashMap<>();
        for (Vote v : allVotes) {
            totalVotes ++;
            OptionCount optionCount = tempMap.get(v.getOption().getId());
            if (optionCount == null) {
                optionCount = new OptionCount();
                optionCount.setOptionId(v.getOption().getId());
                tempMap.put(v.getOption().getId(), optionCount);
            }
            optionCount.setCount(optionCount.getCount() + 1);
            voteResult.setTotalValues(totalVotes);
            voteResult.setTotalVotes(totalVotes);
            voteResult.setResults(tempMap.values());
        }
        return voteResult;
    }
}


//    @Autowired
//    private VoteRepository voteRepository;
//
//    public VoteResult computeResult(Long pollId) {
//        VoteResult voteResult = new VoteResult();
//        Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);
//        //algorithm to count votes
//        int totalVotes = 0;
//        Map<Long, OptionCount> tempMap = new HashMap<>();
//        for (Vote v : allVotes) {
//            totalVotes++;
//            OptionCount optionCount = tempMap.get(v.getOption().getId());
//            if (optionCount == null) {
//                optionCount = new OptionCount();
//                optionCount.setOptionId(v.getOption().getId());
//                tempMap.put(v.getOption().getId(), optionCount);
//            }
//            optionCount.setCount(optionCount.getCount() + 1);
//            voteResult.setTotalVotes(totalVotes);
//            // voteResult.settotalVotes(totalVotes);
//            voteResult.setResults(tempMap.values());
//        }
//            return voteResult;
//        }
//    }

