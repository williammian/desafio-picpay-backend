package br.com.wm.picpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wm.picpay.client.AuthorizationClient;
import br.com.wm.picpay.controller.dto.TransferDto;
import br.com.wm.picpay.exception.PicPayException;

@Service
public class AuthorizationService {

	@Autowired
	private AuthorizationClient authorizationClient;

    public boolean isAuthorized(TransferDto transfer) {

        var resp = authorizationClient.isAuthorized();

        if (resp.getStatusCode().isError()) {
            throw new PicPayException();
        }

        return resp.getBody().authorized();
    }
	
}
