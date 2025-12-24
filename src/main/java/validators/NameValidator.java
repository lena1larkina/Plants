package validators;

import exceptions.ValidationException;

public class NameValidator {
    public void validateNameLength(final String name) {
        if (name == null || name.isEmpty()) {
            throw new ValidationException("Имя не должно быть пустым");
        }
    }
}
