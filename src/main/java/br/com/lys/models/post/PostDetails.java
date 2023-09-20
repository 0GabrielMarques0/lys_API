package br.com.lys.models.post;

public record PostDetails(Long id, String texto, String data, Long id_usuario) {
    public PostDetails(Post post) {
        this(post.getId(), post.getTexto(), post.getData(), post.getVoluntario().getId());
    }
}
