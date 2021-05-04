package com.apress.dto;

import java.util.Collection;

//DTO = data transfer objects, The VoteResult DTO contains the total votes cast and a collection of OptionCount instances
public class VoteResult {

    private int totalValues;
    private int totalVotes;
    private Collection<OptionCount> results;

    public VoteResult() {
    }

    public VoteResult(int totalValues, int totalVotes, Collection<OptionCount> results) {
        this.totalValues = totalValues;
        this.totalVotes = totalVotes;
        this.results = results;
    }

    public int getTotalValues() {
        return totalValues;
    }
    public void setTotalValues(int totalValues) {
        this.totalValues = totalValues;
    }


    public int getTotalVotes() {
        return totalVotes;
    }
    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }


    public Collection<OptionCount> getResults() {
        return results;
    }
    public void setResults(Collection<OptionCount> results) {
        this.results = results;
    }
}
///Users/julianajones/quick-poll-3