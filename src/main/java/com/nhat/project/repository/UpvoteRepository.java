package com.nhat.project.repository;

import com.nhat.project.entity.Upvote;
import com.nhat.project.entity.id.UpvoteId;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

interface UpvoteRepository extends JpaRepository<Upvote, UpvoteId> {

}
