
package uz.pdp.uyvazifacard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import uz.pdp.uyvazifacard.entity.Card;
import uz.pdp.uyvazifacard.entity.InCome;
import uz.pdp.uyvazifacard.entity.OutCome;
import uz.pdp.uyvazifacard.payload.IncomeDTO;
import uz.pdp.uyvazifacard.payload.OutComeDTO;
import uz.pdp.uyvazifacard.repository.CardRepository;
import uz.pdp.uyvazifacard.repository.InComeRepository;
import uz.pdp.uyvazifacard.repository.OutComeRepository;


import javax.persistence.TransactionRequiredException;
import javax.validation.Valid;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class OutComeService {
    @Autowired
    OutComeRepository outComeRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    MyAuthService myAuthService;
    @Autowired
    InComeRepository inComeRepository;

    //create
    public ResponseEntity<?> addOutCome(@Valid OutComeDTO outComeDTO) {
        //malumotlarni  outcomeJadvaliga qoshish uchun yozilgan
        Optional<Card> optionalFromCard = cardRepository.findById(outComeDTO.getFromCardId());
        Optional<Card> optionalToCard = cardRepository.findById(outComeDTO.getToCardId());
        if (!optionalFromCard.isPresent() || !optionalToCard.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("xatolik buday id li kartalar topilmadi pul o'tkazishdagi");

        if (Objects.equals(outComeDTO.getFromCardId(), outComeDTO.getToCardId()))
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("xatolik o'zidan o'ziga o'tkazish shart emas");


        if (outComeDTO.getAmount() * 1.01 > optionalFromCard.get().getBalance())
            return ResponseEntity.status(HttpStatus.CONFLICT).body("1 foiz kommisiyaa bilan cardingizdagi  pul yetarli emas , sizdan ushbu miqdor uchun " + outComeDTO.getAmount() * 1.01 + " som foiz olib qolinadi ");


        OutCome outcome = new OutCome();
        outcome.setAmount(outComeDTO.getAmount());
        outcome.setToCardId(optionalToCard.get());
        outcome.setFromCardId(optionalFromCard.get());
        outcome.setDate(new Date());
        outcome.setCommisionAmount(outComeDTO.getCommisionAmount());
        OutCome save = outComeRepository.save(outcome);


        //malumotlarni income  jadvaliga qoshish uchun yozilgan
        InCome inCome = new InCome();
        inCome.setAmount(save.getAmount());
        inCome.setDate(new Date());
        inCome.setFromCardId(save.getFromCardId());
        inCome.setToCardId(save.getToCardId());

        InCome saved = inComeRepository.save(inCome);

        return ResponseEntity.ok("oktazildi , malumotlar kirim jadvaliga ham saqlandi , kirim id si = "+saved.getId() );
    }

    //reade
    public Page<OutCome> getOutCome(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return outComeRepository.findAll(pageable);
    }

    //read byuSERID
    public Page<OutCome> getOutComeByUserId(int page, String username) {
        UserDetails userDetails = myAuthService.loadUserByUsername(username);
        if (userDetails == null)
            return null;

        Pageable pageable = PageRequest.of(page, 5);
        return outComeRepository.findAllByFromCardId_Username(username, pageable);
    }


    //delete
    public ResponseEntity<?> deleteOutCome(Integer id) {
        try {
            outComeRepository.deleteById(id);
            return ResponseEntity.ok("ochirildi");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Xatolikk");
        }
    }
}
