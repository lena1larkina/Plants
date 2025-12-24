package entities;

public class Profile {
    private int idProfile;
    private String login;
    private String passwordHash;
    private String saltHash;

    public Profile(String login, String passwordHash, String saltHash) {
        this.login = login;
        this.passwordHash = passwordHash;
        this.saltHash = saltHash;
    }

    public Profile(int idProfile, String login, String passwordHash, String saltHash) {
        this.idProfile = idProfile;
        this.login = login;
        this.passwordHash = passwordHash;
        this.saltHash = saltHash;
    }

    public Profile() {
    }

    public int getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(int idProfile) {
        this.idProfile = idProfile;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getSaltHash() {
        return saltHash;
    }

    public void setSaltHash(String saltHash) {
        this.saltHash = saltHash;
    }
}
