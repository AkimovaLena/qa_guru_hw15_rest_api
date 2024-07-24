package models;

import lombok.Data;

@Data
public class LoginBodyRequestModel {
    String email, password;
}
