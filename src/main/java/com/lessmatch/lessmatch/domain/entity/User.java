package com.lessmatch.lessmatch.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 50)
    private String name;
    
    @Column(nullable = false, length = 50)
    private String lastName;
    
    @Column(nullable = false, length = 50)
    private String password;
    
    @Column(nullable = false, length = 50)
    private String icon;
    
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    @OneToMany(mappedBy = "userOne", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Evaluation> evaluationsAsUserOne = new HashSet<>();

    @OneToMany(mappedBy = "userTwo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Evaluation> evaluationsAsUserTwo = new HashSet<>();
}
