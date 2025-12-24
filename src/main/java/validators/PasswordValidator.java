package validators;

import exceptions.ValidationException;

public class PasswordValidator {
    public static void validatePasswordLength(String password) {
        if (password == null || password.length() < 6) {
            throw new ValidationException("Пароль должен быть минимум 6 символов");
        }
    }

    public static void validatePasswordSymbols(String password) {
        if (!password.matches(".*\\d.*")){
            throw new ValidationException("В пароле должна быть как минимум 1 цифра");
        }
    }
}
