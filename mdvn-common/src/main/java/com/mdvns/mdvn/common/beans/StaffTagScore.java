package com.mdvns.mdvn.common.beans;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffTagScore {

    private List<String> tagId;

    private String staffId;

    private Double tagScore;


}
