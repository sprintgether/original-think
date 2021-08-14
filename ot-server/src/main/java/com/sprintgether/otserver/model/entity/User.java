package com.sprintgether.otserver.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Table(name = "ot_user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends MainEntity {

    @Column(unique = true)
    private String email;

    private String password;
    private String firstName;
    private String lastName;
    private String avatar;

    private boolean active = true;
    private boolean subscribeToNewsLetter = true;
    private boolean subscribeToNotification = true;
    private boolean isEmailVerified;

    @Temporal(TemporalType.TIMESTAMP)
    private Date emailVerifiedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ArticleView> articleViews;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ArticleLike> articleLikes;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ot_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_name"))
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role){
        if(roles == null)
            roles = new HashSet<>();
        this.roles.add(role);
    }

    public void removeRole(Role role){
        this.roles.remove(role);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", roles=" + roles +
                '}';
    }

    // Vérifier l'adresse mail
    public void verifyEmail() {
        setEmailVerified(true);
        setEmailVerifiedAt(new Date());
    }


}
