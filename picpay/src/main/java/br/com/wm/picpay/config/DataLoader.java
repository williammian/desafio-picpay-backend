package br.com.wm.picpay.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.wm.picpay.entity.WalletType;
import br.com.wm.picpay.repository.WalletTypeRepository;

@Configuration
public class DataLoader implements CommandLineRunner {

	@Autowired
    private WalletTypeRepository walletTypeRepository;

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(WalletType.Enum.values())
                .forEach(walletType -> walletTypeRepository.save(walletType.get()));
    }
}
