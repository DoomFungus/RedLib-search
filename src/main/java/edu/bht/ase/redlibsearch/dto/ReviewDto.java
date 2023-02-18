package edu.bht.ase.redlibsearch.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ReviewDto {
    @Id
    private Integer id;
    private String name;
    private String text;
    private Integer rating;
}
