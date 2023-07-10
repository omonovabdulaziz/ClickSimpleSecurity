package uz.pdp.uyvazifacard.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeDTO {
    private Integer id;
    private Integer fromCardId;
    private Integer toCardId;
    private long amount;
    private Date date;
}
