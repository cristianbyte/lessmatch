package com.lessmatch.lessmatch.domain.entity;

import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Index;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "evaluations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "evaluations", 
    indexes = {
        @Index(name = "idx_user_one", columnList = "user_one_id"),
        @Index(name = "idx_user_two", columnList = "user_two_id"),
        @Index(name = "idx_song", columnList = "song_id")},
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_one_id", "user_two_id", "song_id"}
    )})
public class Evaluation {

    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_one_id", nullable = false)
    private User userOne;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_two_id", nullable = false)
    private User userTwo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id", nullable = false)
    private Song song;

}
