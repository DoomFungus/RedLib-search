package edu.bht.ase.redlib.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
public class Author {
    @Id
    private String id;
    private String name;
}
