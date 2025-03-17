package br.com.ttech.fp.backend.common.records.security;

import br.com.ttech.fp.backend.common.enums.UserRole;

public record Resgister(String name, String email, String username, String password, UserRole role){
}
