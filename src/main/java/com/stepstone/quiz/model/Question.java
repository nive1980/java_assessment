package com.stepstone.quiz.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Question",uniqueConstraints = {@UniqueConstraint(columnNames = {"question_no", "quiz_category", "quiz_type"}),@UniqueConstraint(columnNames = {"question_name", "quiz_category", "quiz_type"})})
@Getter
@Setter
@NoArgsConstructor
public class Question
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int questionId;

	@Column(name = "question_no")
	private int questionNo;

	@Column(name = "quiz_type")
	private String quizType;

	@Column(name = "quiz_category")
	private String quizCategory;

	@Column(name = "question_name")
	private String questionName;

	@Column(name = "question_optA")
	private String questionOpta;

	@Column(name = "question_optB")
	private String questionOptb;

	@Column(name = "question_optC")
	private String questionOptc;

	@Column(name = "question_optD")
	private String questionOptd;

	@Column(name = "question_optRight")
	private String questionOptright;


}
