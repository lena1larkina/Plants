package repository;



import entities.RarePlant;
import exceptions.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RarePlantsRepository {
    private final String INSERT_QUERY = "INSERT INTO rare_plants(name, rarity_score, id_profile) VALUES (?,?,?)";
    private final String GET_BY_IDPROFILE = "SELECT * FROM rare_plants WHERE id_profile = ?";
    private final String GET_BY_ID = "SELECT * FROM rare_plants WHERE id = ?";
    private final String GET_BY_IDPROFILE_NAME = "SELECT * FROM rare_plants WHERE id_profile = ? AND name LIKE ?";
    private final String UPDATE_QUERY = "UPDATE rare_plants SET name = ? , rarity_score = ? WHERE id = ?";
    private final String DELETE_QUERY = "DELETE FROM rare_plants WHERE id = ?";

    public boolean saveRarePlants(RarePlant rarePlant) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
            ps.setString(1, rarePlant.getName());
            ps.setInt(2, rarePlant.getRarityScore());
            ps.setInt(3, rarePlant.getIdProfile());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public RarePlant getRarePlantById(int id) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new RarePlant(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("rarity_score"),
                        rs.getInt("id_profile")
                );
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return null;
    }

    public List<RarePlant> getRarePlantsByProfileId(int idProfile) {
        List<RarePlant> rarePlants = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_BY_IDPROFILE)) {
            ps.setInt(1, idProfile);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    rarePlants.add(new RarePlant(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("rarity_score"),
                            rs.getInt("id_profile")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return rarePlants;
    }

    public List<RarePlant> getPlantsByIdProfileAndName(int idProfile, String name) {
        List<RarePlant> rarePlants = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_IDPROFILE_NAME)) {
            preparedStatement.setInt(1, idProfile);
            preparedStatement.setString(2, String.format("%%%s%%", name));
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    rarePlants.add(new RarePlant(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("rarity_score"),
                            rs.getInt("id_profile")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return rarePlants;
    }

    public void updateRarePlants(RarePlant rarePlant) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)) {
            ps.setString(1, rarePlant.getName());
            ps.setInt(2, rarePlant.getRarityScore());
            ps.setInt(3, rarePlant.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void deleteRarePlants(int RarePlantId) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_QUERY)) {
            ps.setInt(1, RarePlantId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}
