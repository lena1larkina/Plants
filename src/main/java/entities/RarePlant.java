package entities;

public class RarePlant {
    private int id;
    private String name;
    private int rarityScore;
    private int idProfile;

    public RarePlant(String name, int rarityScore, int idProfile) {
        this.name = name;
        this.rarityScore = rarityScore;
        this.idProfile = idProfile;
    }

    public RarePlant() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRarityScore() {
        return rarityScore;
    }

    public void setRarityScore(int rariryScore) {
        this.rarityScore = rariryScore;
    }

    public int getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(int idProfile) {
        this.idProfile = idProfile;
    }

    public RarePlant(int id, String name, int rariryScore, int idProfile) {
        this.id = id;
        this.name = name;
        this.rarityScore = rariryScore;
        this.idProfile = idProfile;
    }
}
