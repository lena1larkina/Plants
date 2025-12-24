package service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.security.MessageDigest;
import java.util.Objects;
import java.util.logging.Logger;

public class PasswordService {
    private static Logger LOGGER = Logger.getLogger(PasswordService.class.getName());
    public String generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public String hashPassword(String password, String salt) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            LOGGER.severe(e.getMessage());
            return null;
        }
        md.update(Base64.getDecoder().decode(salt));
        byte[] hash = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hash);
    }

    public boolean verifyPassword(String password, String salt, String hashedPassword) {
        return Objects.equals(hashPassword(password, salt), hashedPassword);
    }
}
