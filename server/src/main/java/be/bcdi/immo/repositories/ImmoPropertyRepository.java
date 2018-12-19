package be.bcdi.immo.repositories;

import be.bcdi.immo.entities.ImmoProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class ImmoPropertyRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ImmoPropertyJpaRepository immoPropertyJpaRepository;


    public List<ImmoProperty> saveAll(List<ImmoProperty> properties) {
        return this.immoPropertyJpaRepository.saveAll(properties);
    }


    public List<ImmoProperty> queryRentingPropertyByBedroom(Integer bedroomCount) {
        return this.immoPropertyJpaRepository.queryRentingPropertyByBedroom(bedroomCount);
    }

    public List<ImmoProperty> queryRentingPropertyWithSurfaceIndicated() {
        return this.immoPropertyJpaRepository.queryRentingPropertyWithSurfaceIndicated();
    }

    public List<ImmoProperty> querySellingPropertyWithSurfaceIndicated() {
        return this.immoPropertyJpaRepository.querySellingPropertyWithSurfaceIndicated();
    }

    public List<ImmoProperty> querySellingPropertyByBedroom(Integer bedroomCount) {
        return this.immoPropertyJpaRepository.querySellingPropertyByBedroom(bedroomCount);
    }

    public Optional<ImmoProperty> findById(ImmoProperty.SourceId id) {
        return this.immoPropertyJpaRepository.findById(id);
    }


    // List<ImmoProperty> findBy2RoomsToSale()



}
