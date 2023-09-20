package br.com.lys.models.usuario;

public record UsuarioDetails(Long id, String nome, String email, String senha) {
    public UsuarioDetails(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha());
    }
}
