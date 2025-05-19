package br.com.wm.picpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.wm.picpay.controller.dto.CreateWalletDto;
import br.com.wm.picpay.entity.Wallet;
import br.com.wm.picpay.service.WalletService;
import jakarta.validation.Valid;

@RestController
public class WalletController {
	
	@Autowired
	private WalletService walletService;

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@RequestBody @Valid CreateWalletDto dto) {

        var wallet = walletService.createWallet(dto);

        return ResponseEntity.ok(wallet);
    }

}
