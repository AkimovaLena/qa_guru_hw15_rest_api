package models;

import lombok.Data;

import java.util.Date;

@Data
public class CreateBodyResponseModel {
    String name, job;
    int id;
    Date createdAt;


}
