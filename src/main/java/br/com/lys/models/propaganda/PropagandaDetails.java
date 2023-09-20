package br.com.lys.models.propaganda;

public record PropagandaDetails (Long id, String texto, String data, Long id_usuario) {
    public PropagandaDetails(Propaganda propaganda) {
        this(propaganda.getId(), propaganda.getDescricao(), propaganda.getData(), propaganda.getParceiro().getId());
    }
}
