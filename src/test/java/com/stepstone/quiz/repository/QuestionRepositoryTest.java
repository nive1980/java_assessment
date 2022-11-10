package com.stepstone.quiz.repository;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.stepstone.quiz.model.Question;

/**
 * Tests for Question Repository
 */

@RunWith(MockitoJUnitRunner.class)
public class QuestionRepositoryTest {

    @InjectMocks
    private QuestionRepositoryImpl questionRepository;
    @Mock
    private EntityManager entityManagerMock;

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
    public void findAll_should_return_questions(){
        // given
        Query queryMock = mock(Query.class);
        when(entityManagerMock.createQuery(anyString())).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(questionsMock);
        // when
        List<Question> questions = questionRepository.findAll();

        // then
        assertThat(questions, is(notNullValue()));
        assertThat(questions, is(hasSize(2)));
    }

    @Test
    public void findByQuestionNumber_should_return_questions(){
        // given
        Query queryMock = mock(Query.class);
        when(entityManagerMock.createQuery(anyString())).thenReturn(queryMock);
        when(queryMock.setParameter(anyString(),anyInt())).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(questionsMock.subList(0,1));
        // when
        List<Question> questions = questionRepository.findByQuestionNumber(1);

        // then
        assertThat(questions, is(notNullValue()));
        assertThat(questions, is(hasSize(1)));
    }
    @Test
    public void findByQuestionNumber_cat_type_should_return_questions(){
        // given
        Query queryMock = mock(Query.class);
        when(entityManagerMock.createQuery(anyString())).thenReturn(queryMock);
        when(queryMock.setParameter(anyString(),anyInt())).thenReturn(queryMock);
        when(queryMock.setParameter(anyString(),anyString())).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(questionsMock.subList(0,1));
        // when
        List<Question> questions = questionRepository.findByQuestionNumber(1,"SCI","TEXT");

        // then
        assertThat(questions, is(notNullValue()));
        assertThat(questions, is(hasSize(1)));
    }
    @Test
    public void findByQuizCategory_should_return_questions(){
        // given
        Query queryMock = mock(Query.class);
        when(entityManagerMock.createQuery(anyString())).thenReturn(queryMock);
        when(queryMock.setParameter(anyString(),anyString())).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(questionsMock.subList(0,1));
        // when
        List<Question> questions = questionRepository.findByQuizCategory("SCI");

        // then
        assertThat(questions, is(notNullValue()));
        assertThat(questions, is(hasSize(1)));
    }

    @Test
    public void findByQuizType_should_return_questions(){
        // given
        Query queryMock = mock(Query.class);
        when(entityManagerMock.createQuery(anyString())).thenReturn(queryMock);
        when(queryMock.setParameter(anyString(),anyString())).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(questionsMock.subList(0,1));
        // when
        List<Question> questions = questionRepository.findByQuizType("TEXT");

        // then
        assertThat(questions, is(notNullValue()));
        assertThat(questions, is(hasSize(1)));
    }
    @Test
    public void findByQuestionName_should_return_questions(){
        // given
        Query queryMock = mock(Query.class);
        when(entityManagerMock.createQuery(anyString())).thenReturn(queryMock);
        when(queryMock.setParameter(anyString(),anyString())).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(questionsMock.subList(0,1));
        // when
        List<Question> questions = questionRepository.findByQuestionName("q1");

        // then
        assertThat(questions, is(notNullValue()));
        assertThat(questions, is(hasSize(1)));
    }
    @Test
    public void findByQuestionName_cat_type_should_return_questions(){
        // given
        Query queryMock = mock(Query.class);
        when(entityManagerMock.createQuery(anyString())).thenReturn(queryMock);
        when(queryMock.setParameter(anyString(),anyString())).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(questionsMock.subList(0,1));
        // when
        List<Question> questions = questionRepository.findByQuestionName("q1","SCI","TEXT");

        // then
        assertThat(questions, is(notNullValue()));
        assertThat(questions, is(hasSize(1)));
    }
}
