package kz.bitlab.redis.middle03redis.repository;

import kz.bitlab.redis.middle03redis.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
