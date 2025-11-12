package mss301.mircorservice.msaccount.respone;

import lombok.Data;

@Data

public class LoginResponse {

    private String token;
    private String email;
    private String username;
    private Integer role;

}
