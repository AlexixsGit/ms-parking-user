package com.parking.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.users.exceptions.ApiException;
import com.parking.users.model.User;
import com.parking.users.repository.IUserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Override
	public boolean checkIfUserByCardNumberExists(String cardNumber) throws ApiException {

		User user = this.userRepository.getUserByCardNumber(cardNumber);

		if (user == null) {
			return false;
		}
		try {
			return user.getCard().getNumber().equals(cardNumber);
		} catch (Exception e) {
			throw new ApiException(e);
		}
	}

}
