package io.egen.movieflix.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Scope;

import lombok.Data;

@Entity
@Scope("session")
@Table(name="UserDetail")
@NamedQueries({
		@NamedQuery(name="User.authenticate",query="SELECT u FROM UserEntity u WHERE u.username=:user AND u.password=:pass"),
		@NamedQuery(name="User.findByEmail",query="SELECT u FROM UserEntity u WHERE u.email=:pEmail")
})
@NamedNativeQueries({
	@NamedNativeQuery(name="User.authenticateNative",query="SELECT u.* FROM UserDetail u WHERE u.username=? AND u.password=:? COLLATE SQL_Latin1_General_CP1_CS_AS")
})
@SqlResultSetMapping(
        name = "UserEntityMapping",
        entities = {
            @EntityResult(
                    entityClass = UserEntity.class,
                    fields = {
                        @FieldResult(name = "id", column = "id"),
                        @FieldResult(name = "firstname", column = "firstname"),
                        @FieldResult(name = "lastname", column = "lastname"),
                        @FieldResult(name = "gender", column = "gender"),
                        @FieldResult(name = "phone", column = "phone"),
                        @FieldResult(name = "city", column = "city"),
                        @FieldResult(name = "state", column = "state"),
                        @FieldResult(name = "email", column = "email"),
                        @FieldResult(name = "username", column = "username"),
                        @FieldResult(name = "password", column = "password"),
                        @FieldResult(name = "role", column = "role")
                        })
           })
public @Data class UserEntity {

	@Id
	@GenericGenerator(strategy="uuid2",name="myuuid")
	@GeneratedValue(generator="myuuid")
	private String id;
	
	private String firstname;
	private String lastname;
	private String gender;
	private String phone;
	private String city;
	private String state;
	
	@Column(unique=true, nullable=false)
	private String email;
	
	@Column(unique=true,nullable=false)
	private String username;
	
	@Column(nullable=false)
	private String password;
	
	@Column(columnDefinition="varchar(20) default 'user'")
	private String role="user";
}
