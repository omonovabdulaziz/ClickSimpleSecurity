package uz.pdp.uyvazifacard.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "kartalar")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "usernameni kiritish shart")
    private String username;
    @Column(unique = true, name = "unikalRaqami")
    @NotNull(message = "numberni kiritish shart")
    private long number;
    private long balance;
    private String expiredDate;
    private Boolean active = true;

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }
}
