package com.ketsoft.liteerp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ketsoft.liteerp.dto.RecoveryJwtTokenDTO;
import com.ketsoft.liteerp.dto.UserDTO;
import com.ketsoft.liteerp.model.User;
import com.ketsoft.liteerp.service.UserService;

@RestController
@RequestMapping("/user")
// Classe para controlar as requisições HTTP para /user
public class UserController {

	// Adiciono uma instância automática de UserService
	@Autowired
	UserService service;

	// CONTROLE DOS MÉTODOS GET//

	// getAllUsers -> retorna todos usuários do sistema
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		try {
			List<User> users = service.getAll();
			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// getUserByUsername -> retorna usuário com username correspondente se existir
	@GetMapping("/username/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
		try {
			User user = service.getByUsername(username);
			if (user == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// getById -> retorna um usuário com id correspondente se existir
	@GetMapping("/id/{id}")
	public ResponseEntity<User> getById(@PathVariable String id) {
		Optional<User> user = service.getById(id);
		if (user.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user.get(), HttpStatus.OK);

	}

	// CONTROLE DOS MÉTODOS POST//

	// postUser -> salva um novo usuário no banco de dados
	@PostMapping("/create")
	public ResponseEntity<String> postUser(@RequestBody UserDTO userDto) {
		try {
			String response = service.save(userDto);
			if (response.equals("Usuário cadastrado no sistema.")) {
				return new ResponseEntity<>(response, HttpStatus.CREATED);
			}
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDTO> authenticateUser(@RequestBody UserDTO loginUserDto) {
		RecoveryJwtTokenDTO token = service.authenticateUser(loginUserDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

	// CONTROLE DOS MÉTODOS PATCH//

	@PatchMapping("/id/{id}")
	public ResponseEntity<User> updatePartUserById(@PathVariable String id, @RequestBody User user) {
		try {
			Optional<User> userResponse = service.updatePartById(id, user);
			if (userResponse.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(userResponse.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PatchMapping("/username/{username}")
	public ResponseEntity<User> updatePartUserByUsername(@PathVariable String username, @RequestBody User user) {
		try {
			User userResponse = service.updatePartByUsername(username, user);
			if (userResponse == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(userResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// CONTROLE DOS MÉTODOS PUT//

	@PutMapping("/id/{id}")
	public ResponseEntity<User> updateUserById(@PathVariable String id, @RequestBody User user) {
		try {
			Optional<User> userResponse = service.updateById(id, user);
			if (userResponse.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(userResponse.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/username/{username}")
	public ResponseEntity<User> updateUserByUsername(@PathVariable String username, @RequestBody User user) {
		try {
			User userResponse = service.updateByUsername(username, user);
			if (userResponse == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(userResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// CONTROLE DOS MÉTODOS DELETE//

	@DeleteMapping("/id/{id}")
	public ResponseEntity<HttpStatus> deleteUserById(@PathVariable String id) {
		try {
			return new ResponseEntity<>(service.deleteById(id));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
    @DeleteMapping("/username/{username}")
    public ResponseEntity<HttpStatus> deleteUserByUsername(@PathVariable String username) {
		try {
			return new ResponseEntity<>(service.deleteByUsername(username));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

