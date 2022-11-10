package com.stepstone.quiz.repository;

import java.util.List;

import com.stepstone.quiz.model.Question;

/**
 * @@author Nivedita Singh
 * Question Repository Interface
 */
public interface QuestionRepository {
    /**
     * Returns a list of all the questions in the table Question
     * @return
     */
    List<Question> findAll();
    /**
     * Returns a list of all the questions in the table Question
     * based on questionNumber, Quiz Cateogry and Quiz Type
     * @return
     */
    List<Question> findByQuestionNumber(int questNo,String quizCategory,String quizType);
    /**
     * Returns a list of all the questions in the table Question
     * based on questionNumber
     * @return
     */
    List<Question> findByQuestionNumber(int questNo);

    /**
     * Returns a list of all the questions in the table Question
     * based on questionName, Quiz Cateogry and Quiz Type
     * @return
     */
    List<Question> findByQuestionName(String questName,String quizCategory,String quizType);

    /**
     * Returns a list of all the questions in the table Question
     * based on questionName
     * @return
     */
    List<Question> findByQuestionName(String questName);
    /**
     * Returns a list of all the questions in the table Question
     * based on quizCategory
     * @return
     */
    List<Question> findByQuizCategory(String quizCategory);
    /**
     * Returns a list of all the questions in the table Question
     * based on quizType
     * @return
     */
    List<Question> findByQuizType(String quizType);
}
