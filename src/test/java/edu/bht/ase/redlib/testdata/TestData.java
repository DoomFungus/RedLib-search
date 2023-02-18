package edu.bht.ase.redlib.testdata;

public class TestData {
    public static final String TEST_BOOK_ID = "bookid";
    public static final String TEST_BOOK_NAME = "The Silmarillion";
    public static final String TEST_BOOK_SUMMARY = "A good book";
    public static final String TEST_BOOK_TAG = "Fantasy";
    public static final String TEST_AUTHOR_ID = "authorid";
    public static final String TEST_AUTHOR_NAME = "John Ronald Reuel Tolkien";
    public static final String TEST_SEARCH_EQUALS_FIELD_NAME = "BOOK_NAME";
    public static final String TEST_SEARCH_EQUALS_DB_FIELD_NAME = "name";
    public static final String[] TEST_SEARCH_EQUALS_VALUE = {TEST_BOOK_NAME};
    public static final String TEST_SEARCH_CONTAINS_FIELD_NAME = "AUTHOR_NAME";
    public static final String TEST_SEARCH_CONTAINS_DB_FIELD_NAME = "authors.name";
    public static final String[] TEST_SEARCH_CONTAINS_VALUE = {TEST_AUTHOR_NAME};

}
