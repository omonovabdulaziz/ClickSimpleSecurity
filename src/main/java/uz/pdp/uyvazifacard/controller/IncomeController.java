package uz.pdp.uyvazifacard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.uyvazifacard.entity.InCome;
import uz.pdp.uyvazifacard.repository.InComeRepository;
import uz.pdp.uyvazifacard.service.IncomeService;

@RestController
@RequestMapping("/income")
public class IncomeController {

    @Autowired
    IncomeService incomeService;


    //create
    //ushbu bolim chiqim bolganda avtamatik qoshishi uchun chiqim(outcome)dagi createga bussines logikasi yozilgan



    //read
    @GetMapping
    public Page<InCome> getAllIncome(@RequestParam int page){
        return incomeService.getAllIncome(page);
    }


    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIncome(@PathVariable Integer id){
        return incomeService.deleteIncome(id);
    }
}
