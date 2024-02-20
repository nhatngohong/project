package com.nhat.project.entity;

import com.nhat.project.dto.user.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "users")
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

    @OneToMany(mappedBy = "user_id")
    private List<UpvoteComment> upvotesComment;

    @OneToMany(mappedBy = "user_id")
    private List<UpvotePost> upvotesPost;

    public UserDto convertToDto(){
        return new UserDto(this.id,this.getUsername(),this.getContribution(),this.getCreateDate());
    }
    public OwnerDto convertToOwner(){
        return new OwnerDto(this.getId(), this.getUsername());
    }

}
