package model.entities;

public final class Departamento {
    private String nomeDepartamento;

    public Departamento(String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }

    public String getNomeDepartamento() {
        return nomeDepartamento;
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "nomeDepartamento='" + getNomeDepartamento() + '\'' +
                '}';
    }
}
