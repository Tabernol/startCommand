package servises;

import exeptions.DataBaseException;
import models.Answer;
import repo.AnswerRepo;

import java.sql.SQLException;
import java.util.List;

public class AnswerService {
    AnswerRepo answerRepo = new AnswerRepo();

    public List<Answer> getAnswers(Long questionId) throws DataBaseException{
        return answerRepo.getAnswersByQuestionId(questionId);
    }

    public int createAnswer(Long questionId, String text, boolean result) throws DataBaseException {
        return answerRepo.createAnswer(questionId, text, result);
    }

    public void deleteAnswer(Long id) throws DataBaseException {
        answerRepo.delete(id);
    }

    public List<String> getOnlyOptionAnswers(Long idQuestion) throws DataBaseException {
        return answerRepo.getOptionsAnswerForQuestion(idQuestion);
    }

}
