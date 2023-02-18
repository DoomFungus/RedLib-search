package edu.bht.ase.redlib.controller;

import edu.bht.ase.redlib.dto.BookDto;
import edu.bht.ase.redlib.dto.SearchDto;
import edu.bht.ase.redlib.service.BookSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookSearchController {
    private final BookSearchService bookSearchService;

    @PostMapping()
    public Collection<BookDto> searchForBooks(@RequestBody SearchDto searchDto) {
        return bookSearchService.findBooksByCriteria(searchDto);
    }
}
