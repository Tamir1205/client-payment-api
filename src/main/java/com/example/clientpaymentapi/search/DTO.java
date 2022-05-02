package com.example.clientpaymentapi.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTO {
    private List<String> fields;
    private String searchTerm;
    private String sortBy;
    private SortOrder order;


}

