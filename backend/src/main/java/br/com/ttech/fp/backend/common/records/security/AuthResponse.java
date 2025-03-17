package br.com.ttech.fp.backend.common.records.security;

public record AuthResponse(String _id, String name, String token, String role) {
}
