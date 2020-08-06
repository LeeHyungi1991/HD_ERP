package hd.erp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import hd.erp.dto.EmployeeDTO;
import hd.erp.entity.EmployeeEntity;
import hd.erp.repository.EmployeeRepository;
import hd.erp.security.Role;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService implements UserDetailsService {
	
	private EmployeeRepository employeerepository;
	
	public Long register(EmployeeDTO employeedto) {
		BCryptPasswordEncoder passwordencoder = new BCryptPasswordEncoder();
		employeedto.setHd_pw(passwordencoder.encode(employeedto.getHd_pw()));
		return employeerepository.save(employeedto.toEntity()).getHdcode();
	}
	
	@Override
	public UserDetails loadUserByUsername(String hd_code) throws UsernameNotFoundException {
		Optional<EmployeeEntity> userEntityWrapper = employeerepository.findByhdcode(Long.parseLong(hd_code));
		EmployeeEntity userEntity = userEntityWrapper.get();
		System.out.println("checkval"+userEntity.getHd_level()+","+userEntity.getHd_name());
		String level = userEntity.getHd_level();
		List<GrantedAuthority> authorities = new ArrayList<>();
		if(("¿”º∫¿±").equals(userEntity.getHd_name())) {
			authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
		}else {
			if(level.equals("ADMIN")) {
				authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
			}else if(level.equals("MEMBER")) {
				authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
			}else if(level.equals("USER")) {
				authorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
			}else {
				
			}
			
		}
		return new User(String.valueOf(userEntity.getHdcode()),userEntity.getHd_pw(),authorities);
	}

	
}
