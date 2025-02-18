package com.lessmatch.lessmatch.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    
    @NotNull(message = "Name cannot be null.")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters.")
    private String name;
    
    @NotNull(message = "Last name cannot be null.")
    @Size(min = 3, max = 50, message = "Last name must be between 3 and 50 characters.")
    private String lastName;
    
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,50}$", message = "Password must contain at least one lowercase letter, one uppercase letter, one digit, one special character, and be between 8 and 50 characters long.")
    private String password;
    
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
