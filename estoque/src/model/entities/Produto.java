package model.entities;


import java.util.Objects;

public final class Produto implements Comparable<Produto>{
    private final String nome;
    private final Double preco;
    private Integer quantidade;

    private Departamento departamento;

    public Produto(String nome, Double preco, Departamento departamento, Integer quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.departamento = departamento;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(nome, produto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nome);
    }

    @Override
    public String toString() {
        return getNome() + " - R$"
                + String.format("%.2f", getPreco())
                + " - quantidade: " + getQuantidade();
    }

    @Override
    public int compareTo(Produto o) {
        return getPreco().compareTo(o.getPreco());
    }
}
