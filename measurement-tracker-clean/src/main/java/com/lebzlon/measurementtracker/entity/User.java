package com.lebzlon.measurementtracker.entity;
import jakarta.persistence.*; import java.time.Instant; import java.util.*;
@Entity @Table(name="users",uniqueConstraints=@UniqueConstraint(columnNames="username"))
public class User { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @Column(nullable=false,unique=true) private String username; @Column(nullable=false) private String password; @Column(nullable=false) private Instant createdAt=Instant.now(); @OneToMany(mappedBy="user") private List<Meter> meters=new ArrayList<>();
public Long getId(){return id;} public String getUsername(){return username;} public void setUsername(String u){this.username=u;} public String getPassword(){return password;} public void setPassword(String p){this.password=p;} public Instant getCreatedAt(){return createdAt;}}
