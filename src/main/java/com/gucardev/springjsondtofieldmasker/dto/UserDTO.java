package com.gucardev.springjsondtofieldmasker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gucardev.springjsondtofieldmasker.annotation.MaskData;
import com.gucardev.springjsondtofieldmasker.annotation.MaskData.MaskingOption;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
  @JsonProperty("name")
  private String name;

  @MaskData(maskingOption = MaskingOption.LAST_X_CHARS_CLEAR, value = 5)
  @JsonProperty("idNumber")
  private String idNumber;

  @JsonProperty("accounts")
  private List<AccountDTO> accountDTO;
}
