package com.kor.challenger.domain.dto.response;

import com.kor.challenger.domain.Execution;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ExecutionsResponseDto {
    private List<ExecutionResponseDto> executions;

    public ExecutionsResponseDto(List<Execution> executions) {
        this.executions = new ArrayList<>();

        for (Execution item : executions) {
            this.executions.add(item.toExecutionResponseDto());
        }
    }
}
