package be.bcdi.immo.repositories;

import be.bcdi.immo.entities.ImmoProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImmoPropertyJpaRepository extends JpaRepository<ImmoProperty, ImmoProperty.SourceId> {

    @Query(value = "select * from immo_property i inner join immo_address ia on i.address_id = ia.id where renting_price > 0 and bedroom_count = :bedroomCount", nativeQuery = true)
    List<ImmoProperty> queryRentingPropertyByBedroom(@Param(value = "bedroomCount") Integer bedroomCount);

    @Query(value = "select * from immo_property where selling_price > 0 and bedroom_count = :bedroomCount", nativeQuery = true)
    List<ImmoProperty> querySellingPropertyByBedroom(@Param(value = "bedroomCount") Integer bedroomCount);

    @Query(value = "select * from immo_property where net_habitable_surface > 0 and renting_price > 0", nativeQuery = true)
    List<ImmoProperty> getRentingPropertyWithSurfaceIndicated();

    @Query(value = "select * from immo_property where net_habitable_surface > 0 and selling_price > 0", nativeQuery = true)
    List<ImmoProperty> getSellingPropertyWithSurfaceIndicated();



}
