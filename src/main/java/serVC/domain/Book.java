package serVC.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "isbn")
    private String isbn;


    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "release_date")
    private String releaseDate;


}
