package com.kor.challenger.domain;

import com.kor.challenger.domain.dto.response.ChallengeContentResponseDto;
import lombok.*;

import javax.persistence.*;

//@Entity
//@Table(name = "challenge_content")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
//for value object it is not is an entity object. Value object means does not have real meaning for self individually.
//@EqualsAndHashCode(of = "id")
public class ChallengeContent {
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "challenge_content_id")
    //private Long id;

    private String filename;

    /*@ManyToOne
    @JoinColumn(name = "challenge_id")
    //@JsonBackReference //УБЕРАЕТ ЦИКЛИЧЕСКИЕ ССЫЛКИ
    private Challenge challenge;*/

    /*public ChallengeContentResponseDto getChallengeContentResponseDto(){
        return new ChallengeContentResponseDto(this.filename);
    } */

    public String ge(){
        return "";
    }

    public ChallengeContentResponseDto toChallengeContentResponseDto(){
        return new ChallengeContentResponseDto(filename);
    }
}
