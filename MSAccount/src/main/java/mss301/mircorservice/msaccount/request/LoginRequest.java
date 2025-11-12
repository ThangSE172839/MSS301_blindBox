package mss301.mircorservice.msaccount.request;


import lombok.Data;

@Data

public class LoginRequest {

    private String email;
    private String password;

}

