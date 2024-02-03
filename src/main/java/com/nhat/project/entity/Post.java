package com.nhat.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
@Entity
@Table(name = "post")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    @OneToMany(mappedBy = "postId", cascade = CascadeType.ALL)
    private List<Comment> comments;
    @CreationTimestamp
    private LocalDateTime createDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable()
}
