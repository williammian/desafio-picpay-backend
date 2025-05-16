package br.com.wm.picpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wm.picpay.entity.WalletType;

public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {

}
