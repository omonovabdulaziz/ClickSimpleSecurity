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
import uz.pdp.uyvazifacard.repository.CardRepository;

import java.util.Optional;

@Service
public class CardClickService {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    MyAuthService myAuthService;

    //readAll
    public Page<Card> getAllInfrom(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return cardRepository.findAll(pageable);
    }

    //read
    public Card getInform(Integer id) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        return optionalCard.orElseGet(Card::new);

    }

    //create

    public ResponseEntity<?> addCard(Card card) {
        UserDetails userDetails = myAuthService.loadUserByUsername(card.getUsername());
        if (userDetails == null)
            return ResponseEntity.status(406).body("bunday username yoq");

        boolean existNumber = cardRepository.existsByNumber(card.getNumber());
        if (existNumber)
            return ResponseEntity.status(406).body("bunday nomerli card basada mavjud");

        Card save = cardRepository.save(card);

        return ResponseEntity.ok("saqlandi id si = " + save.getId());
    }

    //delete
    public ResponseEntity<?> deleteCard(Integer id) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (!optionalCard.isPresent())
            return ResponseEntity.status(406).body("bunday id li carta yoq");

        cardRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("deleted");
    }
    //update
    public ResponseEntity<?> updateCard(Integer id , Card card){
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (!optionalCard.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("bunday id li card yoq");
        }
        Card editeCard = optionalCard.get();
        UserDetails userDetails = myAuthService.loadUserByUsername(card.getUsername());
        if (userDetails == null)
            return ResponseEntity.status(406).body("bunday username yoq");

        boolean existNumber = cardRepository.existsByNumber(card.getNumber());
        if (existNumber)
            return ResponseEntity.status(406).body("bunday nomerli card basada mavjud");

        editeCard.setId(id);
        editeCard.setActive(card.getActive());
        editeCard.setBalance(card.getBalance());
        editeCard.setUsername(card.getUsername());
        editeCard.setNumber(card.getNumber());
        editeCard.setExpiredDate(card.getExpiredDate());
        Card save = cardRepository.save(editeCard);
        return ResponseEntity.ok("saqlandi id si = " + save.getId());
    }
}
