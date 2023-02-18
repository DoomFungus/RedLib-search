package edu.bht.ase.redlib.unittests.service;

import com.mongodb.BasicDBList;
import edu.bht.ase.redlib.service.impl.SearchCriteriaBuilder;
import org.assertj.core.api.Assertions;
import org.bson.Document;
import org.junit.jupiter.api.Test;

import java.util.List;

import static edu.bht.ase.redlib.testdata.TestData.*;
import static edu.bht.ase.redlib.testdata.dto.SearchDtoTestData.*;

public class SearchCriteriaBuilderTest {
    @Test
    void should_ConstructCorrectCriteria_When_GivenEqualsNode() {
        var searchDto = aSearchDto(anEqualsNode());

        var criteria = SearchCriteriaBuilder.constructSearchCriteria(searchDto).getCriteriaObject();

        Assertions.assertThat(criteria.get(TEST_SEARCH_EQUALS_DB_FIELD_NAME)).isEqualTo(TEST_SEARCH_EQUALS_VALUE[0]);
    }

    @Test
    void should_ConstructCorrectCriteria_When_GivenContainsNode() {
        var searchDto = aSearchDto(aContainsNode());

        var criteria = SearchCriteriaBuilder.constructSearchCriteria(searchDto).getCriteriaObject();

        Assertions.assertThat(criteria.containsKey(TEST_SEARCH_CONTAINS_DB_FIELD_NAME)).isTrue();
        Document subCriteria = (Document) criteria.get(TEST_SEARCH_CONTAINS_DB_FIELD_NAME);
        Assertions.assertThat(subCriteria.get("$all")).isEqualTo(List.of(TEST_SEARCH_CONTAINS_VALUE));
    }

    @Test
    void should_ConstructCorrectCriteria_When_GivenAndNode() {
        var searchDto = aSearchDto(anAndNode());

        var criteria = SearchCriteriaBuilder.constructSearchCriteria(searchDto).getCriteriaObject();

        Assertions.assertThat(criteria.containsKey("$and")).isTrue();
        BasicDBList subCriteria = (BasicDBList) criteria.get("$and");
        Assertions.assertThat(subCriteria.size()).isEqualTo(2);
    }

    @Test
    void should_ConstructCorrectCriteria_When_GivenOrNode() {
        var searchDto = aSearchDto(anOrNode());

        var criteria = SearchCriteriaBuilder.constructSearchCriteria(searchDto).getCriteriaObject();

        Assertions.assertThat(criteria.containsKey("$or")).isTrue();
        BasicDBList subCriteria = (BasicDBList) criteria.get("$or");
        Assertions.assertThat(subCriteria.size()).isEqualTo(2);
    }

    @Test
    void should_ConstructCorrectCriteria_When_GivenNorNode() {
        var searchDto = aSearchDto(aNorNode());

        var criteria = SearchCriteriaBuilder.constructSearchCriteria(searchDto).getCriteriaObject();

        Assertions.assertThat(criteria.containsKey("$nor")).isTrue();
        BasicDBList subCriteria = (BasicDBList) criteria.get("$nor");
        Assertions.assertThat(subCriteria.size()).isEqualTo(2);
    }
}
