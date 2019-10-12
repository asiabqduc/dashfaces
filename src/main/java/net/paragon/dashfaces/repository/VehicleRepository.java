package net.paragon.dashfaces.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.paragon.dashfaces.domain.entity.Vehicle;

//@RepositoryRestResource(collectionResourceRel = "cars", path = "cars")
@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, UUID> {

    List<Vehicle> findByMake(@Param("make") String make);
}
