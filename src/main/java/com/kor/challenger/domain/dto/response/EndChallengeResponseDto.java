package com.kor.challenger.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class EndChallengeResponseDto {
    private long challengeId;
    private long firstExecutionId;
    private long secondExecutionId;
    private long thirdExecutionId;
}
