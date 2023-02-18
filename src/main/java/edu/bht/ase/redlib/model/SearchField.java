package edu.bht.ase.redlib.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SearchField {
    BOOK_NAME("name", false),
    AUTHOR_NAME("authors.name", true),
    TAG("tags.name", true),
    BOOK_SUMMARY("summary", false);
    public final String searchFieldName;
    public final boolean isMultiple;
}
