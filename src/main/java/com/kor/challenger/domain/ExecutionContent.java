package com.kor.challenger.domain;

import com.kor.challenger.domain.dto.response.ChallengeContentResponseDto;
import com.kor.challenger.domain.dto.response.ExecutionContentResponseDto;
import lombok.*;

import javax.persistence.Embeddable;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ExecutionContent {
    private String filename;

    public ExecutionContentResponseDto toExecutionContentResponseDto(){
        return new ExecutionContentResponseDto(filename);
    }
}
