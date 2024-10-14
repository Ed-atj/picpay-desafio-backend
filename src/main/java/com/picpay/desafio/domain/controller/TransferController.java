package com.picpay.desafio.domain.controller;

import com.picpay.desafio.domain.dto.TransferDto;
import com.picpay.desafio.domain.services.TransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping("/transfer")
    public ResponseEntity<TransferDto> transfer(@RequestBody @Valid TransferDto transferDto){
        TransferDto re = transferService.transfer(transferDto);
        return ResponseEntity.ok(re);
    }


}
