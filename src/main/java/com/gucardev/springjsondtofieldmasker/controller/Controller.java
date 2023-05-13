package com.gucardev.springjsondtofieldmasker.controller;

import com.gucardev.springjsondtofieldmasker.dto.AccountDTO;
import com.gucardev.springjsondtofieldmasker.dto.UserDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  @GetMapping("/user")
  ResponseEntity<List<UserDTO>> getUserData() {
    return ResponseEntity.ok(
        List.of(
            UserDTO.builder()
                .name("Gurkan")
                .idNumber("123456789")
                .accountDTO(
                    List.of(
                        AccountDTO.builder()
                            .accountName("account1")
                            .accountNumber("123456789101112")
                            .build(),
                        AccountDTO.builder()
                            .accountName("account2")
                            .accountNumber("1234567899423246")
                            .build()))
                .build(),
            UserDTO.builder()
                .name("Mehmet")
                .idNumber("987654321")
                .accountDTO(
                    List.of(
                        AccountDTO.builder()
                            .accountName("account3")
                            .accountNumber("123456789101456242")
                            .build(),
                        AccountDTO.builder()
                            .accountName("account4")
                            .accountNumber("1234567899657765434")
                            .build()))
                .build()));
  }
}
