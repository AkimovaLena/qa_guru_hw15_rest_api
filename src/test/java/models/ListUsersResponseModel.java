package models;

import lombok.Data;

import java.util.List;

@Data
public class ListUsersResponseModel {
    int page, per_page, total, total_pages;
    List<DataUserModel> data;
    SupportModel support;
}
