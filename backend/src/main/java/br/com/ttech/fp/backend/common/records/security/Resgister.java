package br.com.ttech.fp.backend.common.dto;

import br.com.ttech.fp.backend.common.enums.UserRole;

public record ResgisterDto (String name, String email, String username, String password, UserRole role){
}
