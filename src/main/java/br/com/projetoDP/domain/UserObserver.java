package br.com.projetoDP.domain;

import br.com.projetoDP.utils.Role;
import br.com.projetoDP.utils.Model;
import br.com.projetoDP.utils.Type;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


@Entity
public class UserObserver extends Model {

    @Column(nullable = false)
    public String nome;

    @Column(nullable = false)
    public String matricula;

    @Column(nullable = false)
    public String email;

    @Column(nullable = false)
    public String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public Role role;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public Type tipo;

    public String getNome() {
        return nome;
    }

    public Type getTipo() {
        return tipo;
    }

    public Role getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getPassword() {
        return password;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setTipo(Type tipo) {
        this.tipo = tipo;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
