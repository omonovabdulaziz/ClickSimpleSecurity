package uz.pdp.uyvazifacard.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.uyvazifacard.entity.OutCome;

public interface OutComeRepository extends JpaRepository<OutCome, Integer> {
    Page<OutCome>  findAllByFromCardId_Username(String fromCardId_username, Pageable pageable);

}

