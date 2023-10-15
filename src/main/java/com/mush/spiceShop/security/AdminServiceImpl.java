package com.mush.spiceShop.security;

import com.mush.spiceShop.domain.Admin;
import com.mush.spiceShop.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

public class AdminServiceImpl implements UserDetailsService {

	@Autowired
	private AdminRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin=repo.getAdminByUsername(username);
		if(admin==null) {
			throw new UsernameNotFoundException("Could not find user");
		}
		return new AdminDetails(admin);
	}

}
