package edu.bht.ase.redlib.service.impl;

import edu.bht.ase.redlib.dto.BookDto;
import edu.bht.ase.redlib.dto.SearchDto;
import edu.bht.ase.redlib.mapper.BookMapper;
import edu.bht.ase.redlib.model.Book;
import edu.bht.ase.redlib.service.BookSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class BookSearchServiceImpl implements BookSearchService {
    private final MongoTemplate mongoTemplate;
    private static final BookMapper bookMapper = BookMapper.INSTANCE;

    @Override
    public Collection<BookDto> findBooksByCriteria(SearchDto searchDto) {
        var query = Query.query(SearchCriteriaBuilder.constructSearchCriteria(searchDto));
        return mongoTemplate.find(query, Book.class).stream().map(bookMapper::bookToBookDto).toList();
    }
}
