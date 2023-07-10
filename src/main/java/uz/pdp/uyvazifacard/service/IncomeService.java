package uz.pdp.uyvazifacard.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.uyvazifacard.entity.InCome;
import uz.pdp.uyvazifacard.repository.InComeRepository;

@Service
public class IncomeService {
    @Autowired
    InComeRepository inComeRepository;


    //read
    public Page<InCome> getAllIncome(int page){
        Pageable  pageable = PageRequest.of(page , 10);
        return inComeRepository.findAll(pageable);
    }
    //delete

    public ResponseEntity<?> deleteIncome(Integer id){
        try {
            inComeRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("deleted");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("xatoo");
        }
    }


}
