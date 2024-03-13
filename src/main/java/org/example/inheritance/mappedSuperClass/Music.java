package org.example.inheritance.mappedSuperClass;


import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "music")
@EqualsAndHashCode(of = "name", callSuper = false)
@ToString(callSuper = true)
public class Music extends BaseEntity<Integer>{
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private GenreTypeMusic genreTypeMusic;
}
