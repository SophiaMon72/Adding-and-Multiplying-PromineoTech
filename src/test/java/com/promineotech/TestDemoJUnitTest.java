package com.promineotech;

import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TestDemoJUnitTest {
  private TestDemo testDemo;

  @BeforeEach
  void setUp() throws Exception {
    testDemo = new TestDemo();
  }

  @ParameterizedTest
  @MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForAddPositive")
  void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected,
      boolean expectException) {
    if (!expectException) {
      assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
    } else {
      assertThatThrownBy(() -> testDemo.addPositive(a, b))
          .isInstanceOf(IllegalArgumentException.class);
    }
  }

  static Stream<Arguments> argumentsForAddPositive() {
    return Stream.of(
        Arguments.of(2, 4, 6, false),
        Arguments.of(0, 5, 5, true),
        Arguments.of(8, 0, 8, true),
        Arguments.of(-9, -6, -15, true),
        Arguments.of(1, -2, -1, true),
        Arguments.of(0, 0, 0, true));

  }

  @Test
  void assertThatPairsOfPositiveNumbersAreAddedCorrectly() {
    assertThat(testDemo.addPositive(7, 2)).isEqualTo(9);
    assertThat(testDemo.addPositive(38, 34)).isEqualTo(72);
  }

  @ParameterizedTest
  @MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForMultiplyPositive")
  void assertThatTwoPositiveNumbersAreMultipliedCorrectly(int a, int b, int expected,
      boolean expectException) {
    if (!expectException) {
      assertThat(testDemo.multiplyPositive(a, b)).isEqualTo(expected);
    } else {
      assertThatThrownBy(() -> testDemo.multiplyPositive(a, b))
          .isInstanceOf(IllegalArgumentException.class);
    }
  }

  static Stream<Arguments> argumentsForMultiplyPositive() {
    return Stream.of(
        Arguments.of(2, 4, 8, false),
        Arguments.of(0, 5, 0, true),
        Arguments.of(8, 0, 0, true),
        Arguments.of(-9, -6, -54, true),
        Arguments.of(1, -2, -2, true),
        Arguments.of(0, 0, 0, true));

  }

  @Test
  void assertThatPairsOfPositiveNumbersAreMultipliedCorrectly() {
    assertThat(testDemo.multiplyPositive(7, 2)).isEqualTo(14);
    assertThat(testDemo.multiplyPositive(6, 12)).isEqualTo(72);
  }

  @Test
  void assertThatNumberSquaredIsCorrect() {
    TestDemo mockDemo = spy(testDemo);
    
    doReturn(5).when(mockDemo).getRandomInt();
    
    int fiveSquared = mockDemo.randomNumberSquared();
    
    assertThat(fiveSquared).isEqualTo(25);
  }
}
