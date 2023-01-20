package servises;

import exeptions.DataBaseException;
import exeptions.QuizException;
import exeptions.ValidateException;
import validator.DataValidator;

import java.util.logging.Level;

public class ValidatorService {
   private String message;

    public void checkFieldsTest(String name, String subject, Integer difficult, Integer duration)
            throws ValidateException {
        DataValidator.validate(DataValidator.validateForNamePlusNumber(name),
                "name must contains only liters and numbers and space from 2-20 symbols");
        DataValidator.validate(DataValidator.validateForName(subject),
                "subject must contains only liters and space from 2-20 symbols");
        DataValidator.validate(DataValidator.validateDifficult(difficult),
                "difficult must be from 1 to 100");
        DataValidator.validate(DataValidator.validateDuration(duration),
                "duration must be from 1 to 30 minutes");
    }

    public void isNameExist(boolean isExist) throws ValidateException {
      DataValidator.validate(!isExist, "Test with this name already exist");
    }

    public void isLoginExist(boolean isExist) throws ValidateException {
        DataValidator.validate(!isExist, "This login already exist");
    }

    public void validateText(String text) throws ValidateException{
        DataValidator.validate(DataValidator.validateForNotLongString(text), "Text answer is too long");
    }

    public void validateFieldsUser(String name,String login,String passwordHash)throws ValidateException{
        DataValidator.validate(DataValidator.validateForName(name),
                "name must contains only liters and numbers and space from 2-20 symbols");
        DataValidator.validate(DataValidator.validateLogin(login),
                "login is invalid");
        DataValidator.validate(DataValidator.validatePassword(passwordHash),
                "password is invalid must 4-10 symbols");
    }
}