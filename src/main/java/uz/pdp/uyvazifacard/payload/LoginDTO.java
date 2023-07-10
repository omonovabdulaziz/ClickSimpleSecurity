package uz.pdp.uyvazifacard.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class LoginDTO {
    private String username;
    private String password;
}
