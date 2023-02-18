package edu.bht.ase.redlib.exception.codes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SearchExceptionCodes implements ExceptionCodes {
    SEARCH_CRITERIA_CONTAINS_ILLEGAL("SEA-001", "Trying to perform contains on no-multiple field");
    public final String reasonCode;
    public final String reasonDescription;
}
