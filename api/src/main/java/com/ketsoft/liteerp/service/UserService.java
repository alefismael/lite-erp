package com.ketsoft.liteerp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ketsoft.liteerp.dto.RecoveryJwtTokenDTO;
import com.ketsoft.liteerp.dto.UserDTO;
import com.ketsoft.liteerp.model.User;
import com.ketsoft.liteerp.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
    private JwtTokenService jwtTokenService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
    public List<User> getAll() {
		return userRepository.findAll();
	}

	public String save(UserDTO userDto) {
		
		// se já existe um usuário com o username informado
		if(getAll().stream()
		        .anyMatch(user -> user.getUsername().equals(userDto.username()))) {
			return "Usuário não cadastrado no sistema, username já está em uso.";
		}
		
		// Cria um novo usuário com os dados fornecidos
        User newUser = new User(userDto.username(), passwordEncoder.encode(userDto.password()));
		userRepository.save(newUser);
		return "Usuário cadastrado no sistema.";
	}

	public User getByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public Optional<User> getById(String id) {
		return userRepository.findById(Long.parseLong(id));
	}

	public Optional<User>  updatePartById(String id, User userUpdate) {
		Optional<User> user = userRepository.findById(Long.parseLong(id));
		if (user.isEmpty()) {
			return null;
		}
		if (userUpdate.getUsername() != null) {
			user.get().setUsername(userUpdate.getUsername());
		}
		if (userUpdate.getPassword() != null) {
			user.get().setPassword(passwordEncoder.encode(userUpdate.getPassword()));
		}
		userRepository.save(user.get());
		return user;
	}

	public User updatePartByUsername(String username, User userUpdate) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			return null;
		}
		if (userUpdate.getUsername() != null) {
			user.setUsername(userUpdate.getUsername());
		}
		if (userUpdate.getPassword() != null) {
			user.setPassword(passwordEncoder.encode(userUpdate.getPassword()));
		}

		userRepository.save(user);
		return user;
	}

	public Optional<User> updateById(String id, User userUpdate) {
		Optional<User> user = userRepository.findById(Long.parseLong(id));
		if (user == null) {
			return null;
		}
		user.get().setUsername(userUpdate.getUsername());
		user.get().setPassword(passwordEncoder.encode(userUpdate.getPassword()));

		userRepository.save(user.get());
		return user;
	}

	public User updateByUsername(String username, User userUpdate) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			return null;
		}
		
		user.setUsername(userUpdate.getUsername());
		user.setPassword(passwordEncoder.encode(userUpdate.getPassword()));

		userRepository.save(user);
		return user;
	}

	public HttpStatus deleteById(String id) {
		Optional<User> user = userRepository.findById(Long.parseLong(id));
		if (user == null) {
			return HttpStatus.NOT_FOUND;
		}
		userRepository.delete(user.get());
		return HttpStatus.OK;
	}

	public HttpStatus deleteByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			return HttpStatus.NOT_FOUND;
		}
		userRepository.delete(user);
		return HttpStatus.OK;
	}
	
	// Método responsável por autenticar um usuário e retornar um token JWT
    public RecoveryJwtTokenDTO authenticateUser(UserDTO userDto) {
        // Cria um objeto de autenticação com o username e a senha do usuário
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDto.username(), userDto.password());

        // Autentica o usuário com as credenciais fornecidas
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // Obtém o objeto UserDetails do usuário autenticado
        User userDetails = (User) authentication.getPrincipal();

        // Gera um token JWT para o usuário autenticado
        return new RecoveryJwtTokenDTO(jwtTokenService.generateToken(userDetails));
    }
}
