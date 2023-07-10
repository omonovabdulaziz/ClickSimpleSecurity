package uz.pdp.uyvazifacard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.uyvazifacard.entity.OutCome;
import uz.pdp.uyvazifacard.payload.OutComeDTO;
import uz.pdp.uyvazifacard.service.OutComeService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/outcome")
public class OutComeController {
    @Autowired
    OutComeService outComeService ;


    //create
    @PostMapping
    public ResponseEntity<?> addOutCome(@Valid @RequestBody OutComeDTO outComeDTO) {
        return outComeService.addOutCome(outComeDTO);
    }


    //read
    @GetMapping
    public Page<OutCome> getOutCome(@RequestParam int page) {
        return outComeService.getOutCome(page);
    }


    //read2
    @GetMapping("/{username}")
    public Page<OutCome> getOutComeByUsername(@PathVariable String username, @RequestParam int page) {
        return outComeService.getOutComeByUserId(page, username);
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOutCome(@PathVariable Integer id) {
        return outComeService.deleteOutCome(id);
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
