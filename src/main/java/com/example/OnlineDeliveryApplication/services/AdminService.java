package com.example.OnlineDeliveryApplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OnlineDeliveryApplication.models.Admin;
import com.example.OnlineDeliveryApplication.repositories.AdminRepository;

@Service
public class AdminService {
	@Autowired
	private AdminRepository adminRepository;
	public Admin insert(Admin admin) {
		// TODO Auto-generated method stub
		return adminRepository.save(admin);
	}
	public boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return adminRepository.existsByEmail(email);
	}

}
