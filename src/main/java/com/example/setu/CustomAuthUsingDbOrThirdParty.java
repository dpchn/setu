package com.example.setu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * We cna use DB or thirdparty   to authenticate request
 * @author dpchn
 *
 */
@Component
public class CustomAuthUsingDbOrThirdParty {

	@Value("${authKey}")
	private String authKey;

	public boolean validate(String key) {
		if (key != null && key.equals(authKey))
			return true;
		return false;
	}
}
