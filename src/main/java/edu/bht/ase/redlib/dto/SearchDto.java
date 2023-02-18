package edu.bht.ase.redlib.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchDto {
    private SearchNode top;

    @Getter
    @Setter
    public static class SearchNode {
        SearchNodeType operation;
        List<SearchNode> children;
        String field;
        String[] value;
    }

    @RequiredArgsConstructor
    public enum SearchNodeType {
        AND(false),
        OR(false),
        NOR(false),
        EQUALS(true),
        CONTAINS(true);

        public final boolean terminalNode;
    }
}
