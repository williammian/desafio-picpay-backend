package br.com.wm.picpay.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wm.picpay.controller.dto.TransferDto;
import br.com.wm.picpay.entity.Transfer;
import br.com.wm.picpay.entity.Wallet;
import br.com.wm.picpay.exception.InsufficientBalanceException;
import br.com.wm.picpay.exception.TransferNotAllowedForWalletTypeException;
import br.com.wm.picpay.exception.TransferNotAuthorizedException;
import br.com.wm.picpay.exception.WalletNotFoundException;
import br.com.wm.picpay.repository.TransferRepository;
import br.com.wm.picpay.repository.WalletRepository;
import jakarta.transaction.Transactional;

@Service
public class TransferService {

	@Autowired
	private TransferRepository transferRepository;
	
	@Autowired
    private AuthorizationService authorizationService;
    
	@Autowired
    private NotificationService notificationService;
    
	@Autowired
    private WalletRepository walletRepository;

    @Transactional
    public Transfer transfer(TransferDto transferDto) {

        var sender = walletRepository.findById(transferDto.payer())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payer()));

        var receiver = walletRepository.findById(transferDto.payee())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payee()));

        validateTransfer(transferDto, sender);

        sender.debit(transferDto.value());
        receiver.credit(transferDto.value());

        var transfer = new Transfer(sender, receiver, transferDto.value());

        walletRepository.save(sender);
        walletRepository.save(receiver);
        var transferResult = transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));

        return transferResult;
    }

    private void validateTransfer(TransferDto transferDto, Wallet sender) {

        if (!sender.isTransferAllowedForWalletType()) {
            throw new TransferNotAllowedForWalletTypeException();
        }

        if (!sender.isBalancerEqualOrGreaterThan(transferDto.value())) {
            throw new InsufficientBalanceException();
        }

        if (!authorizationService.isAuthorized(transferDto)) {
            throw new TransferNotAuthorizedException();
        }

    }
	
}
