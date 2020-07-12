package com.kor.challenger.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "challenge")
@Data
@Setter
@Getter
public class Execution {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
