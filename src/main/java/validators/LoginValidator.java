package validators;

import exceptions.ValidationException;

public class LoginValidator {
    public void validateLoginLength(final String login) {
        if (login == null || login.length() < 4) {
            throw new ValidationException("Логин должен быть минимум 4 символа");
        }
    }
}
