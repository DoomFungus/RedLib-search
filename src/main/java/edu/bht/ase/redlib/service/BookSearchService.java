package edu.bht.ase.redlib.service;

import edu.bht.ase.redlib.dto.BookDto;
import edu.bht.ase.redlib.dto.SearchDto;

import java.util.Collection;

public interface BookSearchService {
    Collection<BookDto> findBooksByCriteria(SearchDto searchDto);
}
