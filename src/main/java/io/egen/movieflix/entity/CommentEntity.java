package io.egen.movieflix.entity;

import io.egen.movieflix.constants.FinalNamedQueries;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import lombok.Data;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Comments")
@NamedQueries({
	//@NamedQuery(name="Comments.getCommentsOnTitle",query=FinalNamedQueries.COMMENTS_ON_TITLE)
})
@SqlResultSetMapping(
        name = "CommentEntityMapping",
        entities = {
            @EntityResult(
                    entityClass = CommentEntity.class,
                    fields = {
                        @FieldResult(name = "id", column = "id"),
                        @FieldResult(name = "user", column = "user_id"),
                        @FieldResult(name = "movie", column = "movie_id"),
                        @FieldResult(name = "comment", column = "comment"),
                        @FieldResult(name = "timeStamp", column = "timeStamp")
                        })
           })
public @Data class CommentEntity {

	@Id
	@GenericGenerator(strategy="uuid2",name="myuuid1")
	@GeneratedValue(generator="myuuid1")
	private String id;
	
	@ManyToOne
	private UserEntity user;
	
	@ManyToOne
	private MovieEntity movie;
	
	private String comment;
	
	private Timestamp timeStamp;
}
