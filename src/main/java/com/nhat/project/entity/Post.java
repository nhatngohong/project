package com.nhat.project.entity;

import com.nhat.project.dto.comment.CommentDto;
import com.nhat.project.dto.post.PostDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "posts")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String title;

    private String content;

    private int upvote;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @CreationTimestamp
    private LocalDateTime createDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToMany(mappedBy = "post_id")
    private List<UpvotePost> upvotesPost;

    public PostDto convertToDto(){
        PostDto postDto = new PostDto();
        postDto.setContent(this.content);
        postDto.setTitle(this.title);
        postDto.setUpvote(this.upvote);
        postDto.setUser(this.owner.convertToDto());
        postDto.setCreateDate(this.getCreateDate());
        postDto.setUpdateDate(this.getUpdateDate());
        List<CommentDto> list = new ArrayList<CommentDto>();
        for (Comment comment:comments){
            list.add(comment.convertToDto());
        }
        //TODO add sort type and paging
        postDto.setComments(list);
        return postDto;
    }
}
