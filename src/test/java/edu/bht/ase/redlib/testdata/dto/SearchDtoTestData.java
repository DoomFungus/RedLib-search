package edu.bht.ase.redlib.testdata.dto;

import edu.bht.ase.redlib.dto.SearchDto;

import java.util.List;

import static edu.bht.ase.redlib.dto.SearchDto.SearchNodeType.*;
import static edu.bht.ase.redlib.testdata.TestData.*;

public class SearchDtoTestData {
    public static SearchDto aSearchDto(SearchDto.SearchNode searchNode) {
        var searchDto = new SearchDto();
        searchDto.setTop(searchNode);
        return searchDto;
    }

    public static SearchDto.SearchNode anEqualsNode() {
        var searchNode = new SearchDto.SearchNode();
        searchNode.setOperation(EQUALS);
        searchNode.setField(TEST_SEARCH_EQUALS_FIELD_NAME);
        searchNode.setValue(TEST_SEARCH_EQUALS_VALUE);
        return searchNode;
    }

    public static SearchDto.SearchNode aContainsNode() {
        var searchNode = new SearchDto.SearchNode();
        searchNode.setOperation(CONTAINS);
        searchNode.setField(TEST_SEARCH_CONTAINS_FIELD_NAME);
        searchNode.setValue(TEST_SEARCH_CONTAINS_VALUE);
        return searchNode;
    }

    public static SearchDto.SearchNode anAndNode() {
        var searchNode = new SearchDto.SearchNode();
        searchNode.setOperation(AND);
        searchNode.setChildren(List.of(anEqualsNode(), aContainsNode()));
        return searchNode;
    }

    public static SearchDto.SearchNode anOrNode() {
        var searchNode = new SearchDto.SearchNode();
        searchNode.setOperation(OR);
        searchNode.setChildren(List.of(anEqualsNode(), aContainsNode()));
        return searchNode;
    }

    public static SearchDto.SearchNode aNorNode() {
        var searchNode = new SearchDto.SearchNode();
        searchNode.setOperation(NOR);
        searchNode.setChildren(List.of(anEqualsNode(), aContainsNode()));
        return searchNode;
    }
}
