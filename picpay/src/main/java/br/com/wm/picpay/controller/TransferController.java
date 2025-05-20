package br.com.wm.picpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.wm.picpay.controller.dto.TransferDto;
import br.com.wm.picpay.entity.Transfer;
import br.com.wm.picpay.service.TransferService;
import jakarta.validation.Valid;

@RestController
public class TransferController {

	@Autowired
	private TransferService transferService;

    @PostMapping("/transfer")
    public ResponseEntity<Transfer> transfer(@RequestBody @Valid TransferDto dto) {

        var resp = transferService.transfer(dto);

        return ResponseEntity.ok(resp);
    }
	
}
