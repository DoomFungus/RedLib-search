package edu.bht.ase.redlib.dto;

import lombok.Data;

import java.util.List;

@Data
public class SearchDto {
    private SearchNode top;

    @Data
    public static class SearchNode{
        SearchNodeType operation;
        List<SearchNode> children;
        String field;
    }

    public enum SearchNodeType{
        AND, OR, NOT, EQUALS, CONTAINS
    }
}
