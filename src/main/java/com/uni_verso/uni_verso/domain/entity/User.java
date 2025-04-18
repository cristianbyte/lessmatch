package com.uni_verso.uni_verso.domain.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(updatable = false, nullable = false, unique = true)
    private String id;
    
    @Column(nullable = false, length = 50)
    private String name;
    
    @Column(nullable = false, length = 50)
    private String icon;

    @OneToMany(mappedBy = "creatorUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Pairing> createdPairings = new HashSet<>();

    @OneToMany(mappedBy = "pairedUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Pairing> joinedPairings = new HashSet<>();
}
