package br.com.ttech.backend.common.records.security;

import br.com.ttech.backend.common.enums.UserRole;

public record Resgister(String name, String email, String username, String password, UserRole role){
}
