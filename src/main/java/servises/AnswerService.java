package servises;

import exeptions.DataBaseException;
import exeptions.ValidateException;
import models.Answer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.AnswerRepo;
import repo.TestRepo;
import validator.DataValidator;

import java.sql.SQLException;
import java.util.List;

/**
 * This class receives data from top-level classes.
 * It checks the input and decides whether to call Answer Repo.class or throw an exception
 */
public class AnswerService {
    private static final Logger logger = LogManager.getLogger(AnswerService.class);
    /**
     * Class contains:
     * answerRepo field for work with AnswerRepo.class
     * validatorService field for validate input date from other
     */
    private AnswerRepo answerRepo;
    private ValidatorService validatorService;


    public AnswerService(AnswerRepo answerRepo, ValidatorService validatorService) {
        this.validatorService = validatorService;
        this.answerRepo = answerRepo;
        logger.info("Creating new AnswerService");
    }

    /**
     * method call repository layer and received list of Answer
     *
     * @param questionId is unique number Question in database
     * @return list of Answer by questionId
     * @throws DataBaseException
     */
    public List<Answer> getAnswers(Long questionId) throws DataBaseException {
        logger.info("SERVICE ANSWER get answers by  question "+ questionId);
        return answerRepo.getAnswersByQuestionId(questionId);
    }

    /**
     * Mhe method takes input, validates it, and calls to repository layer to create Answer
     *
     * @param questionId is unique number Question in database
     * @param text       is new text of answer for question by id
     * @param result     can be true or false, and define the result
     * @return 1 if Answer has created
     * @throws DataBaseException
     * @throws ValidateException
     */
    public int createAnswer(Long questionId, String text, boolean result) throws DataBaseException, ValidateException {
        validatorService.validateText(text);
        logger.info("SERVICE ANSWER creating new answer");
        return answerRepo.createAnswer(questionId, text, result);
    }

    /**
     * The method takes questionId, validates it, and calls to repository layer to delete answer
     *
     * @param id is unique number Answer in database
     * @return 1 if Answer has deleted
     * @throws DataBaseException
     */
    public int deleteAnswer(Long id) throws DataBaseException {
        logger.info("SERVICE ANSWER delete answer "+ id);
        return answerRepo.delete(id);
    }

}
