package br.com.lys.models.diario;

public record DiarioDetails(Long id, String texto, String data, Long id_usuario) {
    public DiarioDetails(Diario diario) {
        this(diario.getId(), diario.getTexto(), diario.getData(), diario.getUsuario().getId());
    }
}
