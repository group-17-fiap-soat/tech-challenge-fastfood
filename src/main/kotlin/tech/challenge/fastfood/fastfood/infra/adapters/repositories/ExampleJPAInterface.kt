package tech.challenge.fastfood.fastfood.infra.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.challenge.fastfood.fastfood.domain.model.ExampleEntity;

@Repository
public interface ExampleJPAInterface extends JpaRepository<ExampleEntity, Long> {

}
