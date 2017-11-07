package com.mdvns.mdvn.staff.sapi.domain.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffTagScore {

    private List<String> tagId;

    private String staffId;

    private Double tagScore;


}
