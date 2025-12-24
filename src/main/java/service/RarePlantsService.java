package service;

import entities.RarePlant;
import exceptions.ValidationException;
import repository.RarePlantsRepository;

import java.util.List;

public class RarePlantsService {
    private final RarePlantsRepository rarePlantsRepository;
    public RarePlantsService(RarePlantsRepository rarePlantsRepository) {
        this.rarePlantsRepository = rarePlantsRepository;
    }
    public boolean saveRarePlants(String plantName, int rarityScore, int idProfile) {
        if (plantName == null || plantName.isBlank()) {
            throw new ValidationException("Название растения не может быть пустым!");
        }
        if (rarityScore <= 0) {
            throw new ValidationException("Оценка редкости должна быть положительной!");
        }
        RarePlant rarePlant = new RarePlant();
        rarePlant.setName(plantName);
        rarePlant.setRarityScore(rarityScore);
        rarePlant.setIdProfile(idProfile);
        return rarePlantsRepository.saveRarePlants(rarePlant);
    }
    public RarePlant getRarePlantById(int id) {
        return rarePlantsRepository.getRarePlantById(id);
    }

    public List<RarePlant> getRarePlantsByProfileId(int idProfile) {
        return rarePlantsRepository.getRarePlantsByProfileId(idProfile);
    }
    public List<RarePlant> getPlantsByProfileIdAndName(int idProfile, String rarePlantName) {
        return rarePlantsRepository.getPlantsByIdProfileAndName(idProfile, rarePlantName);
    }

    public void deleteRarePlant(int rarePlantsId) {
        rarePlantsRepository.deleteRarePlants(rarePlantsId);
    }

    public void updateRarePlants(RarePlant rarePlant) {
        rarePlantsRepository.updateRarePlants(rarePlant);
    }

    public void createRarePlants(RarePlant rarePlant) {
        rarePlantsRepository.saveRarePlants(rarePlant);
    }
}
