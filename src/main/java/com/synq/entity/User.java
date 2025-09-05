package com.synq.entity;
import com.synq.enums.Provider;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="user")
@Table(name="users")
public class User {
    @Id
    private String id;
    @Column(name="user_name",nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(length = 1000)
    private String about;
    @Column(length = 1000)
    private String profilePic;
    private String phoneNumber;
    // Information
    private boolean enabled = false;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    // signUp using self, google, github , linkedin, etc.
    @Enumerated(value= EnumType.STRING)
    private Provider provider = Provider.SELF;
    private String providerId;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL, fetch = FetchType.LAZY ,orphanRemoval = true)
    private List<Contact> contactList = new ArrayList<>();


}
