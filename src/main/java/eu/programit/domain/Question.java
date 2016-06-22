package eu.programit.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;



@Entity
public class Question implements Serializable {
	
	private static final long serialVersionUID = -8245794906215772189L;
	
	//rvandemaat hier moet ik nog wat log naar toe sturen
	private static final Logger log = LoggerFactory.getLogger(Question.class);
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int questionID;

	@OneToMany (mappedBy="question")
	private List<Answer> answers;

	@ManyToOne
	@JoinColumn(name = "categoryid")
	private Category category;

	@Transient
	private boolean correct;

	@Column(columnDefinition = "TEXT")
	private String content;
	
	// might be MEDIUMTEXT here for columnDefinition
	@Column(name = "code", columnDefinition = "TEXT")
	private String code;
	
	private Difficulty difficulty;

	@Column(name = "is_marked", columnDefinition = "INT default 0")
	private boolean isMarked;
	
	private Status status;
	
	@Column(name = "feedback", columnDefinition = "TEXT")
	private String feedback;
	
	@Column(name = "has_feedback", columnDefinition = "INT default 0")
	private boolean hasFeedback;
	
	private String internetLink;

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getQuestionID() {
		return questionID;
	}
	
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		//String newCode= code.replaceAll(Character.toString((char)10),"\n\r");
		//newCode= newCode.replaceAll(Character.toString((char)9),"\t");
		//System.out.println(newCode);
		System.out.println(code);
		this.code = code;
	}
	public Difficulty getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	public boolean isMarked() {
		return isMarked;
	}
	public void setMarked(boolean marked) {
		isMarked = marked;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public boolean isHasFeedback() {
		return hasFeedback;
	}
	public void setHasFeedback(boolean hasFeedback) {
		this.hasFeedback = hasFeedback;
	}
	public String getInternetLink() {
		return internetLink;
	}
	public void setInternetLink(String internetLink) {
		this.internetLink = internetLink;
	}

	@Override
	public String toString() {
		return "Question{" +
				"questionID=" + questionID +
				", content='" + content + '\'' +
				", code='" + code + '\'' +
				", difficulty=" + difficulty +
				", isMarked=" + isMarked +
				", status=" + status +
				", feedback='" + feedback + '\'' +
				", hasFeedback=" + hasFeedback +
				", internetLink='" + internetLink + '\'' +
				'}';
	}

	public void setExam(Exam addedExam) {
		// TODO Auto-generated method stub
		
	}
}




