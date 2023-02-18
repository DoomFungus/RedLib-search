package edu.bht.ase.redlib.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookDto {
    private String id;
    private String name;
    private String summary;
    private List<AuthorDto> authors;
    private List<String> tags;
}
