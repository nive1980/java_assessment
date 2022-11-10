package com.stepstone.quiz.service;

import com.stepstone.quiz.model.Question;
import com.stepstone.quiz.model.QuestionDTO;
import com.stepstone.quiz.repository.QuestionRepository;
import com.stepstone.quiz.repository.QuestionRepositoryImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;


/**
 * Tests for Question Service
 */
@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceTest {

    @InjectMocks
    private QuestionServiceImpl questionService;

    @Mock
    private QuestionRepositoryImpl questionRepository;

    private List<Question> questionsMock = new ArrayList<Question>();

    @Before
    public void createRecords() {
        Question q1 = new Question();
        q1.setQuestionId(1);
        q1.setQuestionName("q1");
        q1.setQuestionOptright("ans1");
        q1.setQuestionNo(1);
        q1.setQuizCategory("SCI");
        q1.setQuizType("TEXT");
        Question q2 = new Question();
        q2.setQuestionId(2);
        q2.setQuestionName("q1");
        q2.setQuestionOptright("ans1");
        q2.setQuestionNo(2);
        q2.setQuizCategory("SCI");
        q2.setQuizType("TEXT");
        questionsMock.add(q1);
        questionsMock.add(q2);

    }

    @Test
    public void getAll_should_return_questions() {
        // when
        when(questionRepository.findAll()).thenReturn(questionsMock);
        List<Question> questions = questionService.getAll();
        // then
        assertThat(questions, is(notNullValue()));
        assertThat(questions, is(hasSize(2)));
    }

    @Test
    public void getAllQuestionsBasedOnCategory_should_return_questions() {
        // when
        when(questionRepository.findByQuizCategory(anyString())).thenReturn(questionsMock);
        List<Question> questions = questionService.getAllQuestionsBasedOnCategory("SCI");
        // then
        assertThat(questions, is(notNullValue()));
        assertThat(questions, is(hasSize(2)));
    }

    @Test
    public void isAnswerRight_should_return_true() {
        // when
        when(questionRepository.findByQuestionNumber(anyInt())).thenReturn(questionsMock);
        when(questionRepository.findByQuizCategory(anyString())).thenReturn(questionsMock);
        when(questionRepository.findByQuizType(anyString())).thenReturn(questionsMock);
        when(questionRepository.findByQuestionNumber(anyInt(),anyString(),anyString())).thenReturn(questionsMock);
        when(questionRepository.findByQuestionName(anyString(),anyString(),anyString())).thenReturn(questionsMock);
        QuestionDTO q1 = new QuestionDTO();
        q1.setQuestionId(1);
        q1.setGivenAnswer("ans1");
        q1.setQuestionNo(1);
        q1.setQuizCategory("SCI");
        q1.setQuizType("TEXT");
        Boolean isRight = questionService.isAnswerRight(q1);
        // then
        assertTrue(isRight);

    }
    @Test
    public void isAnswerRight_should_return_false() {
        // when
        when(questionRepository.findByQuestionNumber(anyInt())).thenReturn(questionsMock);
        when(questionRepository.findByQuizCategory(anyString())).thenReturn(questionsMock);
        when(questionRepository.findByQuizType(anyString())).thenReturn(questionsMock);
        when(questionRepository.findByQuestionNumber(anyInt(),anyString(),anyString())).thenReturn(questionsMock);
        when(questionRepository.findByQuestionName(anyString(),anyString(),anyString())).thenReturn(questionsMock);
        QuestionDTO q1 = new QuestionDTO();
        q1.setQuestionId(1);
        q1.setGivenAnswer("ans11");
        q1.setQuestionNo(1);
        q1.setQuizCategory("SCI");
        q1.setQuizType("TEXT");
        Boolean isRight = questionService.isAnswerRight(q1);
        // then
        assertFalse(isRight);
    }
}
