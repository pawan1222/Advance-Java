package com.cap.BookStroreRest.DataTransferObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserDTO {

    private String email;
    private String password;

}