package uz.pdp.uyvazifacard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.pdp.uyvazifacard.entity.Card;

import javax.persistence.TransactionRequiredException;
import javax.validation.constraints.NotNull;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    boolean existsByNumber(@NotNull(message = "numberni kiritish shart") long number);

    @Query(value = "UPDATE kartalar SET balance = :balance WHERE id = :id", nativeQuery = true)
    TransactionRequiredException  updateByIdBeforeAndBalance(@Param("id") Integer id, @Param("balance") long balance);
}
