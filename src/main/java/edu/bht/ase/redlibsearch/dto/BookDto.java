package edu.bht.ase.redlibsearch.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookDto {
    private Integer id;
    private String name;
    private String summary;
    private List<AuthorDto> authors;
    private List<ReviewDto> reviews;
    private List<String> tags;
}
