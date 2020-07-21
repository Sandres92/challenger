package com.kor.challenger.domain;

import com.kor.challenger.domain.dto.response.ChallengeContentResponseDto;
import lombok.*;

import javax.persistence.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ChallengeContent {
    private String filename;

    public ChallengeContentResponseDto toChallengeContentResponseDto(){
        return new ChallengeContentResponseDto(filename);
    }
}
