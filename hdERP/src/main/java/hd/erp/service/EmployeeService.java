package hd.erp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


//@Slf4j
@Service
//@AllArgsConstructor
public class EmployeeService implements UserDetailsService {
	
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

	
	
	@Autowired
	private EmployeeRepository employeerepository;
	
	public Long register(EmployeeDTO employeedto) {
		log.info("회원가입");
		BCryptPasswordEncoder passwordencoder = new BCryptPasswordEncoder();
		employeedto.setHd_pw(passwordencoder.encode(employeedto.getHd_pw()));
		return employeerepository.save(employeedto.toEntity()).getHdcode();
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String hd_code) throws UsernameNotFoundException {
		Optional<EmployeeEntity> userEntityWrapper = employeerepository.findByhdcode(Long.parseLong(hd_code));
		EmployeeEntity userEntity = userEntityWrapper.get();
		System.out.println("checkval"+userEntity.getHdlevel()+","+userEntity.getHdname());
		String level = userEntity.getHdlevel();
		List<GrantedAuthority> authorities = new ArrayList<>();
		if(("임성윤").equals(userEntity.getHdname())) {
			authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
			log.info("ADMIN부여");
		}else {
			if(level.equals("ADMIN")) {
				authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
				log.info("ADMIN부여");
			}else if(level.equals("MEMBER")) {
				authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
				log.info("MEMBER부여");
			}else if(level.equals("USER")) {
				authorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
				log.info("USER부여");
			}else {
				log.info("권한미부여");
			}
			
		}
		return new User(String.valueOf(userEntity.getHdcode()),userEntity.getHdpw(),authorities);
	}

	
}
