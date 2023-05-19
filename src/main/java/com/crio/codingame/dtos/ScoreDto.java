package com.crio.codingame.dtos;

import java.util.List;

public class ScoreDto implements Comparable<ScoreDto>{
    private String userName;
    private int totalScore;
    private List<String> questions;
    
    public ScoreDto(String userName, int totalScore, List<String> questions) {
        this.userName = userName;
        this.totalScore = totalScore;
        this.questions = questions;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "\"" + userName + "\" : " + totalScore + " " + questions;
    }   

    @Override
    public int compareTo(ScoreDto sdt){
        return sdt.getTotalScore() - this.getTotalScore();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((questions == null) ? 0 : questions.hashCode());
        result = prime * result + totalScore;
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ScoreDto other = (ScoreDto) obj;
        if (questions == null) {
            if (other.questions != null)
                return false;
        } else if (!questions.equals(other.questions))
            return false;
        if (totalScore != other.totalScore)
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        return true;
    }
}
