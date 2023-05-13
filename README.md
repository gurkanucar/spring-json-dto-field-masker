# JSON Field Masker Annotation

this project inspired from **Java Techie**
[https://www.youtube.com/watch?v=7TaUhLQQPDs](https://www.youtube.com/watch?v=7TaUhLQQPDs)
After I see that, just added some extra options and wrote unit tests.

```java
  @MaskData(replaceChar = "*", maskingOption = MaskingOption.LAST_X_CHARS_MASKED, value = 10)
  private String accountNumber;
```

**Options**
```java

  int value() default 3;

  String replaceChar() default "x";

  MaskingOption maskingOption() default MaskingOption.LAST_X_CHARS_CLEAR;

  enum MaskingOption {
    FIRST_X_CHARS_CLEAR,
    FIRST_X_CHARS_MASKED,
    LAST_X_CHARS_CLEAR,
    LAST_X_CHARS_MASKED
  }
  
  
```


**examples**
```json
[
    {
        "name": "Gurkan",
        "idNumber": "1234xxxxx",
        "accounts": [
            {
                "accountName": "account1",
                "accountNumber": "*****6789101112"
            },
            {
                "accountName": "account2",
                "accountNumber": "******7899423246"
            }
        ]
    },
    {
        "name": "Mehmet",
        "idNumber": "9876xxxxx",
        "accounts": [
            {
                "accountName": "account3",
                "accountNumber": "********9101456242"
            },
            {
                "accountName": "account4",
                "accountNumber": "*********9657765434"
            }
        ]
    }
]
```