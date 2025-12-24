package validators;

import exceptions.ValidationException;

public class RarityScoreValidator {
    public void validateScoreNumber(final String rarityScore) {
        try {
            Integer.parseInt(rarityScore);
        } catch (Throwable e) {
            throw new ValidationException("Оценка должна быть числом");
        }
    }
    public void validateScorePositive(final String rarityScore) {
        if (Integer.parseInt(rarityScore) <= 0) {
            throw new ValidationException("Оценка должна быть больше 0");
        }
    }
}
