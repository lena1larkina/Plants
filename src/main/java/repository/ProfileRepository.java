package repository;

import entities.Profile;
import exceptions.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ProfileRepository {
    private static final String INSERT_ID = "INSERT INTO profile(login, password_hash, salt) VALUES (?, ?, ?)";
    private static final String GET_BY_LOGIN = "SELECT id_profile, login, password_hash, salt FROM profile WHERE login = ?";
    private static final String CHECK_LOGIN_EXIST = "SELECT id_profile FROM profile WHERE login = ?";

    public void save(Profile profile) throws DBException {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(INSERT_ID);
            ps.setString(1, profile.getLogin());
            ps.setString(2, profile.getPasswordHash());
            ps.setString(3, profile.getSaltHash());
            if (ps.executeUpdate() == 0) {
                throw new DBException("Не удалось добавить профиль");
            }
        } catch (SQLException e) {
            throw new DBException("Ошибка в работе с базой данных", e);
        }
    }

    public Profile getByLogin(String login) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(GET_BY_LOGIN);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Profile(
                        rs.getInt("id_profile"),
                        rs.getString("login"),
                        rs.getString("password_hash"),
                        rs.getString("salt")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean checkLoginExist(String login) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(CHECK_LOGIN_EXIST);
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}
