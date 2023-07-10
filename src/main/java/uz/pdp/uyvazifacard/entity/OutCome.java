package uz.pdp.uyvazifacard.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class OutCome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Card fromCardId;
    @ManyToOne
    private Card toCardId;
    private long amount;
    private Date date;
    private long commisionAmount;
}
