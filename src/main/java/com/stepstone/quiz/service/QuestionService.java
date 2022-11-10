package com.stepstone.quiz.service;

import java.util.List;

import com.stepstone.quiz.model.Question;
import com.stepstone.quiz.model.QuestionDTO;

/**
 *  Question Service Interface
 */
public interface QuestionService {
    List<Question> getAll();
    Boolean isAnswerRight(QuestionDTO questionDTO);
    List<Question> getAllQuestionsBasedOnCategory(String category);
}
