package com.cap.BookStroreRest.DataTransferObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDTO {

    private String username;
    private String email;
    private String password;

}