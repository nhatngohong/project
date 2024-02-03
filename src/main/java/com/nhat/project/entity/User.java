package com.nhat.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true)
    private String username;
    private String password;
    private String roles;
    private int contribution;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Post> posts;
    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL)
    private List<Comment> comments;
    private LocalDateTime createDate;

}