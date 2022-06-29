package com.example.junit1;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Convert;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class JUnitRepeatingTest {

    @DisplayName("스터디")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void repeating_test(RepetitionInfo info){
        System.out.println("test" + info.getCurrentRepetition() + "/" +
                info.getTotalRepetitions());
        assertTrue(true);
    }

    @DisplayName("스터디 만들기")
    @ParameterizedTest(name = "[{index}] {displayName} message={0}")
    @ValueSource(strings = {"날씨가", "많이", "추워지고", "있네요"})
    @NullAndEmptySource
    void repeating_test_var(String message) {
        System.out.println(message);
    }

    @ParameterizedTest(name = "[{index}] {displayName} message={0}")
    @ValueSource(ints = {10, 20, 30})
    void converter_parameterizedTest(@ConvertWith(StudyConverter.class) Study study) {
        System.out.println(study.getLimit());
    }

    @ParameterizedTest(name = "[{index}] {displayName} message={0}")
    @CsvSource({"19, '자바 스터디'", "20, 스프링"})
    void parameterTest(int limit, String name) {
        Study study = new Study(limit, name);
        System.out.println(study.getName());
    }

    @ParameterizedTest(name = "[{index}] {displayName} message={0}")
    @CsvSource({"19, '자바 스터디'", "20, 스프링"})
    void parameterizedTest(ArgumentsAccessor argumentsAccessor) {
        Study study = new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
        System.out.println(study.getName());
    }

    @ParameterizedTest(name = "[{index}] {displayName} message={0}")
    @CsvSource({"19, '자바 스터디'", "20, 스프링"})
    void aggregator_parameterizedTest(@AggregateWith(StudyAggregator.class) Study study) {
        System.out.println(study.getName());
    }

    static class StudyAggregator implements ArgumentsAggregator {

        @Override
        public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
            return new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
        }
    }

    static class StudyConverter extends SimpleArgumentConverter {

        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(Study.class, targetType, "Can Only convert to Study");
            return new Study(Integer.parseInt(source.toString()));
        }
    }
}
