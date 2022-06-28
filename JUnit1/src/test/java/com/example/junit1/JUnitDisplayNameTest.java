package com.example.junit1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class JUnitDisplayNameTest {

    @Test
    void context_loads() {
    }

    @Test
    @DisplayName("Study \uD83D\uDE00")
    void create_new_study() {

    }

}
