package com.stepstone.quiz.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.stepstone.quiz.model.Question;

/**
 * Static Question Repository implementation
 */
@Repository
public class QuestionRepositoryImpl implements QuestionRepository {
    private static final Logger logger = LoggerFactory.getLogger(QuestionRepositoryImpl.class);
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Question> findAll() {
        logger.info("QuestionRepositoryImpl --> findAll");
        try
        {
            return entityManager.createQuery("FROM Question q order by questionNo asc").getResultList();
        }
        catch (NoResultException e)
        {
            logger.info("QuestionRepositoryImpl --> findAll exception {}",e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<Question> findByQuestionNumber(int questNo) {
        logger.info("QuestionRepositoryImpl --> findByQuestionNumber");
        try
        {
            return entityManager.createQuery("FROM Question q where q.questionNo =: questNo  order by questionNo asc").setParameter("questNo",questNo).getResultList();
        }
        catch (NoResultException e)
        {
            logger.info("QuestionRepositoryImpl --> findByQuestionNumber exception {}",e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<Question> findByQuestionNumber(int questNo,String quizCategory,String quizType) {
        logger.info("QuestionRepositoryImpl --> findByQuestionNumber");
        try
        {
            return entityManager.createQuery("FROM Question q where q.questionNo =: questNo and q.quizCategory=:quizCategory and q.quizType=:quizType order by questionNo asc").setParameter("questNo",questNo).setParameter("quizCategory",quizCategory).setParameter("quizType",quizType).getResultList();
        }
        catch (NoResultException e)
        {
            logger.error("QuestionRepositoryImpl --> findByQuestionNumber exception {}",e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<Question> findByQuizCategory(String quizCategory)
    {
        try
        {
            logger.info("QuestionRepositoryImpl --> findByQuizCategory");
            return entityManager.createQuery("FROM Question q where q.quizCategory =: quizCategory order by questionName asc").setParameter("quizCategory",quizCategory).getResultList();
        }
        catch (NoResultException e)
        {
            logger.error("QuestionRepositoryImpl --> findByQuizCategory exception {}",e.getMessage());

            return Collections.emptyList();
        }
    }
    @Override
    public List<Question> findByQuizType(String quizType)
    {
        try
        {
            logger.info("QuestionRepositoryImpl --> findByQuizType");
            return entityManager.createQuery("FROM Question q where q.quizType =: quizType order by questionName asc").setParameter("quizType",quizType).getResultList();
        }
        catch (NoResultException e)
        {
            logger.error("QuestionRepositoryImpl --> findByQuizType exception {}",e.getMessage());
            return Collections.emptyList();
        }
    }
    @Override
    public List<Question> findByQuestionName(String questName) {
        logger.info("QuestionRepositoryImpl -->  findByQuestionName ");
        try
        {
            return entityManager.createQuery("FROM Question q where q.questionName =: questName  order by questionNo asc").setParameter("questName",questName).getResultList();
        }
        catch (NoResultException e)
        {
            logger.error("QuestionRepositoryImpl -->  findByQuestionName exception {}",e.getMessage());
            return Collections.emptyList();
        }
    }
    @Override
    public List<Question> findByQuestionName(String questName,String quizCategory,String quizType) {
        logger.info("QuestionRepositoryImpl -->  findByQuestionName" );
        try
        {
            return entityManager.createQuery("FROM Question q where q.questionName =: questName and q.quizCategory=:quizCategory and q.quizType=:quizType order by questionNo asc").setParameter("questName",questName).setParameter("quizCategory",quizCategory).setParameter("quizType",quizType).getResultList();
        }
        catch (NoResultException e)
        {
            logger.error("QuestionRepositoryImpl -->  findByQuestionName exception {}",e.getMessage());
            return Collections.emptyList();
        }
    }
}
