package br.com.lys.models.voluntario;

public record VoluntarioDetails(Long id, String nome, String email, String telefone, String senha) {

    public VoluntarioDetails(Voluntario voluntario) {
        this(voluntario.getId(), voluntario.getNome(), voluntario.getEmail(), voluntario.getTelefone(), voluntario.getSenha());
    }
}
