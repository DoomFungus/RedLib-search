package edu.bht.ase.redlib.service.impl;

import edu.bht.ase.redlib.dto.SearchDto;
import edu.bht.ase.redlib.exception.codes.SearchExceptionCodes;
import edu.bht.ase.redlib.exception.ex.IllegalArgumentException;
import edu.bht.ase.redlib.model.SearchField;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

import static edu.bht.ase.redlib.dto.SearchDto.SearchNodeType.CONTAINS;
import static edu.bht.ase.redlib.dto.SearchDto.SearchNodeType.EQUALS;

public class SearchCriteriaBuilder {
    private static final Map<SearchDto.SearchNodeType, Function<Criteria[], Criteria>> combinerCriteriaMap = new EnumMap<>(SearchDto.SearchNodeType.class);

    static {
        combinerCriteriaMap.put(SearchDto.SearchNodeType.AND, x -> new Criteria().andOperator(x));
        combinerCriteriaMap.put(SearchDto.SearchNodeType.OR, x -> new Criteria().orOperator(x));
        combinerCriteriaMap.put(SearchDto.SearchNodeType.NOR, x -> new Criteria().norOperator(x));
    }

    private SearchCriteriaBuilder() {
    }

    public static Criteria constructSearchCriteria(SearchDto searchDto) {
        var top = searchDto.getTop();
        return constructCriteriaFromNode(top);
    }

    private static Criteria constructCriteriaFromNode(SearchDto.SearchNode node) {
        if (!node.getOperation().terminalNode) {
            return constructCriteriaFromNonTerminalNode(node);
        } else {
            return constructCriteriaFromTerminalNode(node);
        }
    }

    private static Criteria constructCriteriaFromNonTerminalNode(SearchDto.SearchNode node){
        var childrenCriteria = node.getChildren()
                .stream()
                .map(SearchCriteriaBuilder::constructCriteriaFromNode).toArray(Criteria[]::new);
        return combinerCriteriaMap.get(node.getOperation()).apply(childrenCriteria);
    }

    private static Criteria constructCriteriaFromTerminalNode(SearchDto.SearchNode node){
        SearchField searchField = SearchField.valueOf(node.getField());
        if (EQUALS.equals(node.getOperation())) {
            if (!searchField.isMultiple) {
                return Criteria.where(searchField.searchFieldName).is(node.getValue()[0]);
            } else throw new IllegalArgumentException(SearchExceptionCodes.SEARCH_CRITERIA_EQUALS_ILLEGAL);
        } else if (CONTAINS.equals(node.getOperation())) {
            if (searchField.isMultiple) {
                return Criteria.where(searchField.searchFieldName).all((Object[]) node.getValue());
            } else throw new IllegalArgumentException(SearchExceptionCodes.SEARCH_CRITERIA_CONTAINS_ILLEGAL);
        } else throw new UnsupportedOperationException();
    }
}
