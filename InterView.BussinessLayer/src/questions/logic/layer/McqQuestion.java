package questions.logic.layer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import MCQ.Db.MCQDataBase;
import questions.Utility.AnswerOptionPojo;
import questions.Utility.QuestionPojo;

public class McqQuestion {

	private QuestionPojo mypojo = null;
	private List<AnswerOptionPojo> myOptions = new ArrayList<AnswerOptionPojo>();
	
	public McqQuestion(QuestionPojo pojo, List<AnswerOptionPojo> allAnswers) {
		this.mypojo = pojo;
		
		
		// ==============1===================
		for(AnswerOptionPojo itm : allAnswers) {
			if(itm.questionId == pojo.questionId) {
				myOptions.add(itm);
			}
		}
		
		// ===============2===============
		myOptions = allAnswers.stream().filter(itm -> itm.questionId == pojo.questionId).collect(Collectors.toList());
		
	}

	public String getQuesion() {
		return this.mypojo.question;
	}

	public int getQuestionId()
	{
		return this.mypojo.questionId;
	}

	public String getCurrectAnswer() {
		return this.mypojo.correctOption;
	}

	// get all question randomly from question_paper db
	public static List<McqQuestion> showQuestions() throws ClassNotFoundException, SQLException {
		MCQDataBase mcqDB = new MCQDataBase();
		List<QuestionPojo> questionPojo = mcqDB.getQuestions();
		List<McqQuestion> questions = new ArrayList<>();
		for (QuestionPojo pojo : questionPojo) {
			questions.add(new McqQuestion(pojo, myOptions));
		}
		return questions;
	}

}