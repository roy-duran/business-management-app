package com.example.Sprint7Final.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TeamAndMemberInfoAndProjectAmountDto {

    private Long id;

    private List<MemberOfTeamDto> members;

    private Long numberOfProjects;
    
    // Requested by David P
    private String teamName;

}
