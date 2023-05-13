package com.gucardev.springjsondtofieldmasker.annotation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.lang.reflect.Field;

public class MaskDataSerializer extends JsonSerializer {

  @Override
  public void serialize(
      Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
      throws IOException {

    MaskData annotation =
        getCurrentField(jsonGenerator, serializerProvider).getAnnotation(MaskData.class);

    if (annotation == null) {
      jsonGenerator.writeString(o.toString());
      return;
    }

    String obfuscatedText = o.toString();
    int charCount = annotation.value();
    String replaceChar = annotation.replaceChar();
    obfuscatedText =
        switch (annotation.maskingOption()) {
          case LAST_X_CHARS_CLEAR -> lastXCharsClear(obfuscatedText, charCount, replaceChar);
          case LAST_X_CHARS_MASKED -> lastXCharsMasked(obfuscatedText, charCount, replaceChar);
          case FIRST_X_CHARS_CLEAR -> firstXCharsClear(obfuscatedText, charCount, replaceChar);
          case FIRST_X_CHARS_MASKED -> firstXCharsMasked(obfuscatedText, charCount, replaceChar);
        };
    jsonGenerator.writeString(obfuscatedText);
  }

  private String lastXCharsClear(String text, int value, String replaceChar) {
    int size = text.length();
    if (value > size) {
      throw new IllegalArgumentException("Value cannot be greater than the length of the string");
    }
    String clearText = text.substring(0, size - value);
    String obscuredText = text.substring(size - value);
    obscuredText = obscuredText.replaceAll(".", replaceChar);
    return clearText + obscuredText;
  }

  private String lastXCharsMasked(String text, int value, String replaceChar) {
    int size = text.length();
    if (value > size) {
      throw new IllegalArgumentException("Value cannot be greater than the length of the string");
    }
    String obscuredText = text.substring(0, size - value);
    String clearText = text.substring(size - value);
    obscuredText = obscuredText.replaceAll(".", replaceChar);
    return obscuredText + clearText;
  }

  private String firstXCharsClear(String text, int value, String replaceChar) {
    int size = text.length();
    if (value > size) {
      throw new IllegalArgumentException("Value cannot be greater than the length of the string");
    }
    String obscuredText = text.substring(value);
    String clearText = text.substring(0, value);
    obscuredText = obscuredText.replaceAll(".", replaceChar);
    return clearText + obscuredText;
  }

  private String firstXCharsMasked(String text, int value, String replaceChar) {
    int size = text.length();
    if (value > size) {
      throw new IllegalArgumentException("Value cannot be greater than the length of the string");
    }
    String clearText = text.substring(value);
    String obscuredText = text.substring(0, value);
    obscuredText = obscuredText.replaceAll(".", replaceChar);
    return obscuredText + clearText;
  }

  private Field getCurrentField(
      JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
    try {
      return jsonGenerator
          .getOutputContext()
          .getCurrentValue()
          .getClass()
          .getDeclaredField(jsonGenerator.getOutputContext().getCurrentName());
    } catch (NoSuchFieldException e) {
      throw new IllegalArgumentException("Unable to retrieve current field.", e);
    }
  }
}
