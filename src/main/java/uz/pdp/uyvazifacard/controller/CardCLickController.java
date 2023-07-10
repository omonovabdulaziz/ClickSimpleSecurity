package uz.pdp.uyvazifacard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.uyvazifacard.entity.Card;
import uz.pdp.uyvazifacard.service.CardClickService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/card")
public class CardCLickController {
    @Autowired
    CardClickService cardService;


    @GetMapping
    public Page<Card> getAllInfrom(@RequestParam int page) {
        return cardService.getAllInfrom(page);
    }


    @GetMapping("/{id}")
    public Card getById(@PathVariable Integer id) {
        return cardService.getInform(id);
    }

    @PostMapping
    public ResponseEntity<?> addCard(@Valid @RequestBody Card card) {
        return cardService.addCard(card);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable Integer id) {
        return cardService.deleteCard(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCard(@Valid @RequestBody Card card , @PathVariable Integer id){
        return cardService.updateCard(id , card);
    }





    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
