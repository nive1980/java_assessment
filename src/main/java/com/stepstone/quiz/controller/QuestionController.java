package com.stepstone.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import com.stepstone.quiz.model.Question;
import com.stepstone.quiz.model.QuestionDTO;
import com.stepstone.quiz.service.QuestionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * Question Controller class
 * @@author Nivedita Singh
 * This is Rest controller for all the Quiz end points.
 * Assumption in this application is that a question can belong to a particular category
 * (SCI/GEO hardcoded for now which can be moved to a different table) and a particular type(MULTI -for multiple choice
 * and TEXT for a text based answer.A question can be searched based on either a question name or number.
 */
@RestController
@RequestMapping("/quiz")
@Api("/quiz")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/questionNames")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 403, message = "Disabled"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Get all Question Names")
    /**
     * This method returns all the Question names(questions as list of strings)
     */
    public List<String> getAllQuestionNames() {
        return questionService.getAll().stream().map(Question:: getQuestionName).collect(Collectors.toList());
    }

    @GetMapping("/questions")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 403, message = "Disabled"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Get all Questions")
    /**
     * This returns a list of all questions as a list of QuestionDTO objects
     */
    public List<QuestionDTO> getAll() {
        return questionService.getAll().stream().map(item ->  new QuestionDTO(item.getQuestionId(), item.getQuestionNo(), item.getQuestionName(), item.getQuizCategory(), item.getQuizType())).collect(Collectors.toList());
    }

    @GetMapping("/questionsBasedOnCategory/{category}")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 403, message = "Disabled"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Get all Questions by Category Name")
    /**
     * Returns a list of QuestionDTOs based on Category name passed.
     * As of now the allowed categories are SCI and GEO.
     * But this can be enhanced by storing a list of categories in a database table called Category which would
     * have a foreign key in the Question table
     */
    public List<QuestionDTO> getAllQuestionsBasedOnCategory(@PathVariable("category") String category){
        List<Question> questions = questionService.getAllQuestionsBasedOnCategory(category);
        return questions.stream().map(item ->  new QuestionDTO(item.getQuestionId(), item.getQuestionNo(), item.getQuestionName(), item.getQuizCategory(), item.getQuizType())).collect(Collectors.toList());

    }

    @RequestMapping(value = "/isAnswerRight", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiResponses(value={
        @ApiResponse(code = 200, message = "Boolean true if answer is right boolean false otherwise", response=boolean.class)
    })
    /**
     * Returns a boolean if the given answer is right or wrong
     */
    public Boolean isAnswerRight(@RequestBody QuestionDTO questionDTO)
    {
        return questionService.isAnswerRight(questionDTO);
    }


}
