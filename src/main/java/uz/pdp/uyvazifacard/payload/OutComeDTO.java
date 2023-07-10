package uz.pdp.uyvazifacard.payload;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class OutComeDTO {
    private Integer id;
    private Integer fromCardId;
    private Integer toCardId;
    private long amount;
    private Date date;
    private long commisionAmount;

}
