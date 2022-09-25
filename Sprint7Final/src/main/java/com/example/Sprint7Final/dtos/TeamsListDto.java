package com.example.Sprint7Final.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TeamsListDto {

    private List<TeamAndMemberInfoAndProjectAmountDto> teams;

}
