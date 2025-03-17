package br.com.ttech.fp.backend.service.impl;


import br.com.ttech.fp.backend.common.dto.AuthResponse;
import br.com.ttech.fp.backend.common.dto.AuthenticationDto;
import br.com.ttech.fp.backend.common.dto.ResgisterDto;
import br.com.ttech.fp.backend.common.dto.ResponseDto;
import br.com.ttech.fp.backend.common.entity.User;
import br.com.ttech.fp.backend.common.enums.Messages;
import br.com.ttech.fp.backend.common.exception.BadRequestException;
import br.com.ttech.fp.backend.common.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.util.Objects.isNull;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public ResponseDto userValidate(AuthenticationDto data , String token){
        ResponseDto response = new ResponseDto();
        User user = userRepository.findByUsername(data.username());
        response.setCode(HttpStatus.OK.value());
        response.setDateTime(LocalDateTime.now());
        response.setMessage(Messages.OPERATION_EXECUTED_SUCCESSFULLY.getMessage());

        AuthResponse authResponse = new AuthResponse(user.getId(), user.getName(), token, user.getRole() );
        response.setData(authResponse);
        return response;
    }

    public ResponseDto register(ResgisterDto register){
        ResponseDto response = new ResponseDto();

        String encryptPassword = new BCryptPasswordEncoder().encode(register.password());
        var user = userRepository.findByUsername(register.username());
        if (!isNull(user)) throw new BadRequestException(Messages.USERNAME_NOT_AVAILABLE.getMessage());
        var newUser = new User(register.name(), register.email(), register.username(),
                encryptPassword, register.role());
        this.userRepository.save(newUser);
        return response;

    }
}
