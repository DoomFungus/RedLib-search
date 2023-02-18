package edu.bht.ase.redlib.unittests.service;

import com.mongodb.BasicDBList;
import edu.bht.ase.redlib.exception.codes.SearchExceptionCodes;
import edu.bht.ase.redlib.exception.ex.IllegalArgumentException;
import edu.bht.ase.redlib.service.impl.SearchCriteriaBuilder;
import org.assertj.core.api.Assertions;
import org.bson.Document;
import org.junit.jupiter.api.Test;

import java.util.List;

import static edu.bht.ase.redlib.testdata.TestData.*;
import static edu.bht.ase.redlib.testdata.dto.SearchDtoTestData.*;

class SearchCriteriaBuilderTest {
    @Test
    void should_ConstructCorrectCriteria_When_GivenEqualsNode() {
        var searchDto = aSearchDto(anEqualsNode());

        var criteria = SearchCriteriaBuilder.constructSearchCriteria(searchDto).getCriteriaObject();

        Assertions.assertThat(criteria).containsEntry(TEST_SEARCH_EQUALS_DB_FIELD_NAME, TEST_SEARCH_EQUALS_VALUE[0]);
    }

    @Test
    void should_ConstructCorrectCriteria_When_GivenContainsNode() {
        var searchDto = aSearchDto(aContainsNode());

        var criteria = SearchCriteriaBuilder.constructSearchCriteria(searchDto).getCriteriaObject();

        Assertions.assertThat(criteria).containsKey(TEST_SEARCH_CONTAINS_DB_FIELD_NAME);
        Document subCriteria = (Document) criteria.get(TEST_SEARCH_CONTAINS_DB_FIELD_NAME);
        Assertions.assertThat(subCriteria).containsEntry("$all", List.of(TEST_SEARCH_CONTAINS_VALUE));
    }

    @Test
    void should_ConstructCorrectCriteria_When_GivenAndNode() {
        var searchDto = aSearchDto(anAndNode());

        var criteria = SearchCriteriaBuilder.constructSearchCriteria(searchDto).getCriteriaObject();

        Assertions.assertThat(criteria).containsKey("$and");
        BasicDBList subCriteria = (BasicDBList) criteria.get("$and");
        Assertions.assertThat(subCriteria).hasSize(2);
    }

    @Test
    void should_ConstructCorrectCriteria_When_GivenOrNode() {
        var searchDto = aSearchDto(anOrNode());

        var criteria = SearchCriteriaBuilder.constructSearchCriteria(searchDto).getCriteriaObject();

        Assertions.assertThat(criteria).containsKey("$or");
        BasicDBList subCriteria = (BasicDBList) criteria.get("$or");
        Assertions.assertThat(subCriteria).hasSize(2);
    }

    @Test
    void should_ConstructCorrectCriteria_When_GivenNorNode() {
        var searchDto = aSearchDto(aNorNode());

        var criteria = SearchCriteriaBuilder.constructSearchCriteria(searchDto).getCriteriaObject();

        Assertions.assertThat(criteria).containsKey("$nor");
        BasicDBList subCriteria = (BasicDBList) criteria.get("$nor");
        Assertions.assertThat(subCriteria).hasSize(2);
    }

    @Test
    void should_ThrowIllegalArgumentException_When_GivenEqualsNodeWithMultipleField() {
        var equalsNode = anEqualsNode();
        equalsNode.setField(TEST_SEARCH_CONTAINS_FIELD_NAME);
        var searchDto = aSearchDto(equalsNode);

        String expectedReasonCode = SearchExceptionCodes.SEARCH_CRITERIA_EQUALS_ILLEGAL.reasonCode;
        String expectedReasonDescription = SearchExceptionCodes.SEARCH_CRITERIA_EQUALS_ILLEGAL.reasonDescription;
        Assertions.assertThatThrownBy(() -> SearchCriteriaBuilder.constructSearchCriteria(searchDto))
                .isInstanceOf(IllegalArgumentException.class)
                .matches(x -> expectedReasonCode.equals(((IllegalArgumentException) x).getReasonCode()))
                .matches(x -> expectedReasonDescription.equals(((IllegalArgumentException) x).getReasonDescription()));
    }

    @Test
    void should_ThrowIllegalArgumentException_When_GivenContainsNodeWithSingleField() {
        var containsNode = aContainsNode();
        containsNode.setField(TEST_SEARCH_EQUALS_FIELD_NAME);
        var searchDto = aSearchDto(containsNode);

        String expectedReasonCode = SearchExceptionCodes.SEARCH_CRITERIA_CONTAINS_ILLEGAL.reasonCode;
        String expectedReasonDescription = SearchExceptionCodes.SEARCH_CRITERIA_CONTAINS_ILLEGAL.reasonDescription;
        Assertions.assertThatThrownBy(() -> SearchCriteriaBuilder.constructSearchCriteria(searchDto))
                .isInstanceOf(IllegalArgumentException.class)
                .matches(x -> expectedReasonCode.equals(((IllegalArgumentException) x).getReasonCode()))
                .matches(x -> expectedReasonDescription.equals(((IllegalArgumentException) x).getReasonDescription()));
    }
}
