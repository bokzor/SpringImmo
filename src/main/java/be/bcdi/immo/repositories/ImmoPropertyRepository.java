package be.bcdi.immo.repositories;

import be.bcdi.immo.entities.ImmoProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImmoPropertyRepository extends JpaRepository<ImmoProperty, Long> {

}
