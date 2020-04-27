package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name="cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // quem vai gerar a primarykey será o banco de dados
    private Integer Id;


    private String nome;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Id.equals(cliente.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }
}
