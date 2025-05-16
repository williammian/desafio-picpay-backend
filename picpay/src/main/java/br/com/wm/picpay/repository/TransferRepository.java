package br.com.wm.picpay.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wm.picpay.entity.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, UUID> {
	
}
