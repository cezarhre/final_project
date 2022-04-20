package ro.fasttrackit.finalproject.model;

import org.springframework.stereotype.Component;

@Component
public class Result {

    private int id;
    private int totalCorrect=0;
    private int totalWrong=0;

    public Result(){
    }

    public Result(int totalCorrect, int totalWrong){
        this.totalCorrect = totalCorrect;
        this.totalWrong = totalWrong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalCorrect() {
        return totalCorrect;
    }

    public void setTotalCorrect(int totalCorrect) {
        this.totalCorrect = totalCorrect;
    }

    public int getTotalWrong() {
        return totalWrong;
    }

    public void setTotalWrong(int totalWrong) {
        this.totalWrong = totalWrong;
    }
}
