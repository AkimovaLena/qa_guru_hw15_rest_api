package models;

import lombok.Data;

@Data
public class DataUserModel {
    int id;
    String email, first_name, last_name, avatar;
}
