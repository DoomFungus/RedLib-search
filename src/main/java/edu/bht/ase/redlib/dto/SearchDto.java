package edu.bht.ase.redlib.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
public class SearchDto {
    private SearchNode top;

    @Data
    public static class SearchNode{
        SearchNodeType operation;
        List<SearchNode> children;
        String field;
        String[] value;
    }

    @RequiredArgsConstructor
    public enum SearchNodeType{
        AND(false),
        OR(false),
        NOR(false),
        EQUALS(true),
        CONTAINS(true);

        public final boolean terminalNode;
    }
}
