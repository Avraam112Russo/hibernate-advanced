package org.example.inheritance.mappedSuperClass;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "genre")
@EqualsAndHashCode(of = "name", callSuper = false)
@ToString(exclude = "listOfMusic", callSuper = true)
public class GenreTypeMusic extends BaseEntity<Long> {

    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @OneToMany(mappedBy = "genreTypeMusic", cascade = CascadeType.PERSIST)
    @Builder.Default
    private List<Music> listOfMusic = new ArrayList<>();

    public void addMusicToList(Music music){
        listOfMusic.add(music);
        music.setGenreTypeMusic(this);
    }
}
