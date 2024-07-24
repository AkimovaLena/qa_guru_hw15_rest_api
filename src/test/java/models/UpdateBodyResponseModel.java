package models;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateBodyResponseModel {
    String name, job;
    Date updatedAt;


}
