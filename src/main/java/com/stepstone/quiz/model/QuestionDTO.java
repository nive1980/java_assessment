package com.stepstone.quiz.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class QuestionDTO
{
	private String questionName;
	private String givenAnswer;
	private int questionId;
	private String quizType;

	private int questionNo;
	private String quizCategory;

	public QuestionDTO(int questionId,int questionNo,String questionName,String quizCategory,String quizType){
		this.questionId = questionId;
		this.questionNo = questionNo;
		this.questionName = questionName;
		this.quizCategory = quizCategory;
		this.quizType = quizType;
	}

}
