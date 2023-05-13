package com.gucardev.springjsondtofieldmasker.annotation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MaskDataSerializerTest {

  private MaskDataSerializer serializer;

  @BeforeEach
  public void setup() {
    serializer = new MaskDataSerializer();
  }

  @ParameterizedTest
  @CsvSource({"123456789,5,x,xxxx56789", "abcdefgh,7,*,*bcdefgh"})
  void testLastXCharsClear(String text, int value, String replaceChar, String expected) {
    String actual = serializer.lastXCharsClear(text, value, replaceChar);
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @CsvSource({"123456789,5,x,1234xxxxx", "abcdefgh,5,*,abc*****"})
  void testLastXCharsMasked(String text, int value, String replaceChar, String expected) {
    String actual = serializer.lastXCharsMasked(text, value, replaceChar);
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @CsvSource({"123456789,3,x,123xxxxxx", "abcdefgh,5,*,abcde***"})
  void testFirstXCharsClear(String text, int value, String replaceChar, String expected) {
    String actual = serializer.firstXCharsClear(text, value, replaceChar);
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @CsvSource({"123456789,5,x,xxxxx6789", "abcdefgh,2,*,**cdefgh"})
  void testFirstXCharsMasked(String text, int value, String replaceChar, String expected) {
    String actual = serializer.firstXCharsMasked(text, value, replaceChar);
    assertEquals(expected, actual);
  }
}
