package com.stepstone.quiz.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import com.stepstone.quiz.model.Question;
import com.stepstone.quiz.model.QuestionDTO;
import com.stepstone.quiz.service.QuestionService;

/**
 * Tests for Question Controller
 */
@RunWith(MockitoJUnitRunner.class)
public class QuestionControllerTest {
    @InjectMocks
    private QuestionController questionController;
    @Mock
    private QuestionService questionService;

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
    public void getQuestions_should_return_questions() {
        // given
        given(questionService.getAll()).willReturn(questionsMock);
        //when
        List<QuestionDTO> questiondtos = questionController.getAll();
        // then
        assertThat(questiondtos.size(), is(2));
    }
    @Test
    public void getQuestionNames_should_return_questionNames() {
        // given
        given(questionService.getAll()).willReturn(questionsMock);
        //when
        List<String> questiondtos = questionController.getAllQuestionNames();
        // then
        assertThat(questiondtos.size(), is(2));
    }

    @Test
    public void getAllQuestionNamesBasedOnCategory_should_return_questions() {
        // given
        given(questionService.getAllQuestionsBasedOnCategory(anyString())).willReturn(questionsMock);
        //when
        List<QuestionDTO> questiondtos = questionController.getAllQuestionsBasedOnCategory(anyString());
        // then
        assertThat(questiondtos.size(), is(2));
    }

    @Test
    public void whenAnswerIsRight_should_return_true() {
        // given
        QuestionDTO q1 = new QuestionDTO();
        q1.setQuestionId(1);
        q1.setQuestionName("q1");
        q1.setGivenAnswer("ans1");
        q1.setQuestionNo(1);
        q1.setQuizCategory("SCI");
        q1.setQuizType("TEXT");
        given(questionService.isAnswerRight(q1)).willReturn(true);
        //when
        boolean isRight = questionController.isAnswerRight(q1);
        // then
        assertTrue(isRight);
    }
    @Test
    public void whenAnswerIsWrong_should_return_false() {
        // given
        QuestionDTO q1 = new QuestionDTO();
        q1.setQuestionId(1);
        q1.setQuestionName("q1");
        q1.setGivenAnswer("ans11");
        q1.setQuestionNo(1);
        q1.setQuizCategory("SCI");
        q1.setQuizType("TEXT");
        given(questionService.isAnswerRight(q1)).willReturn(false);
        //when
        boolean isRight = questionController.isAnswerRight(q1);
        // then
        assertFalse(isRight);
    }
}
