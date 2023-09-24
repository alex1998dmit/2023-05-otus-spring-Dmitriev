package com.example.homework6.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "middleName", nullable = false)
    private String middleName;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "author", fetch=FetchType.LAZY)
    private List<Book> book;

    public String getFullName() {
        return this.getFirstName() + " " + this.getMiddleName() + " " + this.getLastName();
    }
}