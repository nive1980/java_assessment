package com.stepstone.quiz.service;

import com.stepstone.quiz.exception.QuizException;
import com.stepstone.quiz.model.Question;
import com.stepstone.quiz.model.QuestionDTO;
import com.stepstone.quiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Question Service implementation
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(final QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    public List<Question> getAll() {
        return this.questionRepository.findAll();
    }

    @Override
    public List<Question> getAllQuestionsBasedOnCategory(String category) {
        if(isEmptyOrNullCheck(category)) {
            throw new QuizException("Please specify a Category(GEO/SCI) for the Quiz");
        }
        if(!isEmptyOrNullCheck(category)) {
            List<Question> questions = this.questionRepository.findByQuizCategory(category);
            if(questions.isEmpty()) {
                throw new QuizException("Please specify a valid Category");
            }
            return questions;
        }
        return Collections.emptyList();
    }
    @Override
    public Boolean isAnswerRight(QuestionDTO questionDTO)
    {
        if (questionDTO.getQuestionNo() < 1 && (isEmptyOrNullCheck(questionDTO.getQuestionName())))
        {
            throw new QuizException("Please specify either a question Number or Question Name");
        }
        else if (isEmptyOrNullCheck(questionDTO.getGivenAnswer()))
        {
            throw new QuizException("Please specify an answer to check for validity");
        }
        if (isEmptyOrNullCheck(questionDTO.getQuizCategory()) || isEmptyOrNullCheck(questionDTO.getQuizType()))
        {
            throw new QuizException("Please specify a Category(GEO/SCI) and Type(MULTI/TEXT) for the Quiz");
        }
        if (questionDTO.getQuestionNo() > 0)
        {
            List<Question> questions = this.questionRepository.findByQuestionNumber(questionDTO.getQuestionNo());
            if (questions.isEmpty())
            {
                throw new QuizException("Please specify a valid question Number");
            }
        }
        if(!isEmptyOrNullCheck(questionDTO.getQuestionName())) {
            List<Question> questions = this.questionRepository.findByQuestionName(questionDTO.getQuestionName());
            if (questions.isEmpty())
            {
                throw new QuizException("Please specify a valid question");
            }
        }
        if(!isEmptyOrNullCheck(questionDTO.getQuizCategory())) {
            List<Question> questions = this.questionRepository.findByQuizCategory(questionDTO.getQuizCategory());
            if(questions.isEmpty()) {
                throw new QuizException("Please specify a valid Category");
            }
        }
        if(!isEmptyOrNullCheck(questionDTO.getQuizType())) {
            List<Question> questions = this.questionRepository.findByQuizType(questionDTO.getQuizType());
            if(questions.isEmpty()) {
                throw new QuizException("Please specify a valid Type");
            }
        }
        if(questionDTO.getQuestionNo() > 0)
        {
            List<Question> questions1 = this.questionRepository.findByQuestionNumber(questionDTO.getQuestionNo(), questionDTO.getQuizType(), questionDTO.getQuizCategory());
            Question record = questions1.get(0);
            String answer = record.getQuestionOptright();
            return (questionDTO.getGivenAnswer().trim().equalsIgnoreCase(answer.trim()));
        }
        else if(!isEmptyOrNullCheck(questionDTO.getQuestionName()))
        {
            List<Question> questions1 = this.questionRepository.findByQuestionName(questionDTO.getQuestionName(),  questionDTO.getQuizCategory(),questionDTO.getQuizType());
            Question record = questions1.get(0);
            String answer = record.getQuestionOptright();
            return (questionDTO.getGivenAnswer().trim().equalsIgnoreCase(answer.trim()));
        }
        return true;
    }

    private boolean isEmptyOrNullCheck(String str) {
        return str==null || str.isEmpty();
    }
}
