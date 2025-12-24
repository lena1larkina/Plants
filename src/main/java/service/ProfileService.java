package service;

import entities.Profile;
import exceptions.ValidationException;
import repository.ProfileRepository;

public class ProfileService {
    private final ProfileRepository profileRepository;
    private final PasswordService passwordService;

    public ProfileService(ProfileRepository profileRepository, PasswordService passwordService) {
        this.profileRepository = profileRepository;
        this.passwordService = passwordService;
    }

    public Profile createProfile(String login, String password) throws ValidationException {
        if (profileRepository.checkLoginExist(login)) {
            throw new ValidationException(String.format("Логин '%s' уже существует", login));
        }
        String salt = passwordService.generateSalt();
        String hashPass = passwordService.hashPassword(password, salt);
        Profile profile = new Profile(login, hashPass, salt);
        profileRepository.save(profile);
        profile.setIdProfile(login(login, password));
        return profile;
    }

    public Integer login(String login, String password) {
        Profile profile = profileRepository.getByLogin(login);
        if (profile == null) return null;
        boolean passwordValid = passwordService.verifyPassword(password, profile.getSaltHash(), profile.getPasswordHash());
        return passwordValid ? profile.getIdProfile() : null;
    }
}
