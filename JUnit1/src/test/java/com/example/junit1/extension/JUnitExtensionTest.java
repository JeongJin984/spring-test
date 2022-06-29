package com.example.junit1.extension;

import com.example.junit1.Tags.SlowTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

//@ExtendWith(JUnitExtension.class)   // 선언적으로 사용
public class JUnitExtensionTest {

    @RegisterExtension
    JUnitExtension jUnitExtension = new JUnitExtension(1000L);

    @Test
    @DisplayName("No Slow Test")
    void slow_test() throws InterruptedException {
        Thread.sleep(1005L);
    }

    @SlowTest
    @DisplayName("Slow Test")
    void slow_test2() throws InterruptedException {
        Thread.sleep(1005L);
    }
}
