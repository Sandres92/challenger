package com.kor.challenger.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kor.challenger.domain.dto.response.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "execution")
@Data
@Setter
@Getter
public class Execution {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "challenge_id")
    //@JsonBackReference //УБЕРАЕТ ЦИКЛИЧЕСКИЕ ССЫЛКИ
    private Challenge challenge;

    @ElementCollection
    private List<ExecutionContent> executionContents = new ArrayList<>(10);

    @OneToMany(mappedBy = "execution", orphanRemoval = true)
    private List<ExecutionComment> executionComments = new ArrayList<>();

    public ExecutionResponseDto toExecutionResponseDto() {
        ExecutionResponseDto executionResponseDto = new ExecutionResponseDto();
        executionResponseDto.setId(this.id);
        executionResponseDto.setDescription(this.description);
        executionResponseDto.setCreationDate(this.creationDate);

        UserResponseDto userResponseDto = author.toUserResponseDto();
        executionResponseDto.setAuthor(userResponseDto);

        List<ExecutionContentResponseDto> contentDtos = new ArrayList<>();
        for (ExecutionContent item : executionContents) {
            contentDtos.add(item.toExecutionContentResponseDto());
        }
        executionResponseDto.setExecutionContents(contentDtos);

        List<ExecutionCommentResponseDto> commentResponseDtos = new ArrayList<>();
        for (ExecutionComment item : executionComments) {
            commentResponseDtos.add(item.toExecutionCommentResponseDto());
        }
        executionResponseDto.setExecutionComments(commentResponseDtos);

        executionResponseDto.setChallengeMini(new ChallengeMiniResponseDto(challenge));

        return executionResponseDto;
    }
}
