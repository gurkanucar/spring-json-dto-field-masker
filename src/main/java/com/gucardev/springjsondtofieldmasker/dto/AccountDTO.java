package com.gucardev.springjsondtofieldmasker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gucardev.springjsondtofieldmasker.annotation.MaskData;
import com.gucardev.springjsondtofieldmasker.annotation.MaskData.MaskingOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
  @JsonProperty("accountName")
  private String accountName;

  @JsonProperty("accountNumber")
  @MaskData(replaceChar = "*", maskingOption = MaskingOption.LAST_X_CHARS_MASKED, value = 10)
  private String accountNumber;
}
