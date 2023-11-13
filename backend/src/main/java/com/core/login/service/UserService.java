package com.core.login.service;

import java.util.Optional;

public interface UserService<T> {

	Optional<T> getMyUserWithAuthorities();


}
