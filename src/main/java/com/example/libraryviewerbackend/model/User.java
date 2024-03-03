package com.example.libraryviewerbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name="user_id")
    private Integer id;
    private String username;
    private String password;
    private String email;
    //TODO do dodania constrainy 1;1 jak na wygenerowanym DTO
    //TODO do rozdzielenia pliki schemy tak jak u damiana w:
    // https://github.com/DGolonka/activity-monitor-spec/blob/master/activity-monitor-spec/v1/openapi.yaml
}
