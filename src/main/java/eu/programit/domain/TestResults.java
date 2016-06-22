package eu.programit.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
@Entity
public class TestResults implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1491385510338892825L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int testResultId;


    private int examId;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    // map questions to answers
    @Column(columnDefinition = "BLOB")
    @ElementCollection(targetClass = ArrayList.class)
    //Key = questionID  value list of answerIDs
    private Map<Integer, List<Integer>> testResults = new HashMap<>();

    public int getTestResultId() {
        return testResultId;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<Integer, List<Integer>> getTestResults() {
        return testResults;
    }

    public void setTestResults(Map<Integer, List<Integer>> testResults) {
        this.testResults = testResults;
    }

    /******
     * Utility Mehtods
     ******************************************************/

    public void printValues() {
        if (testResults.size() < 1)
            return;
        for (Integer q : testResults.keySet()) {
            if (testResults.get(q) == null)
                continue;
            for (Integer a : testResults.get(q)) {
                System.out.println("Question: " + q + " | Answer: " + a);
            }
        }
        System.out.println("Nr of answers: " + testResults.size());


    }

    /******
     * Getters & Setters
     ******************************************************/

    public List<Integer> getTestResults(int questionNr) {
        return testResults.get(questionNr);
    }

    public void setTestResults(Integer question, List<Integer> answerList) {
        this.testResults.put(question, answerList);
    }

    @Override
    public String toString() {
        return "TestResults{" +
                "id=" + testResultId +
                ", examId=" + examId +
               ", user=" + user +
                ", testResults=" + testResults +
                '}';
    }
}