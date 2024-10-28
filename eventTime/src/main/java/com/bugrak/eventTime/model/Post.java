package com.bugrak.eventTime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int id;

    @NonNull
    @Column(name = "post_content")
    private String content;

    @NonNull
    @Temporal(TemporalType.DATE)
    @Column(name = "post_date")
    private LocalDate eventTime;

    @Column(name = "post_image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // user_id dış anahtar olacak
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false) // city_id dış anahtar olacak
    private City city;


}
