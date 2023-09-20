package br.com.lys.models.parceiro;

public record ParceiroDetails(Long id, String nome, String telefone, String email, String endereco) {
    public ParceiroDetails(Parceiro parceiro) {
        this(parceiro.getId(), parceiro.getNome(), parceiro.getTelefone(), parceiro.getEmail(), parceiro.getCnpj());
    }
}
