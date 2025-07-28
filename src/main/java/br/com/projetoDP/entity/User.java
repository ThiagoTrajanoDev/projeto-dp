package br.com.projetoDP.entity;

import br.com.projetoDP.utils.Model;
import br.com.projetoDP.utils.Type;
import io.quarkus.security.jpa.*;
import jakarta.persistence.*;


@Entity
@UserDefinition
@Table(name="userobserver")
public class User extends Model {

    @Column(nullable = false)
    public String nome;

    @Column(nullable = false)
    public String matricula;

    @Column(nullable = false)
    @Username
    public String email;

    @Column(nullable = false)
    @Password(value= PasswordType.CLEAR)
    public String password;

    @Column(nullable = false)
    @Roles
    public String role;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public Type tipo;

    public String getNome() {
        return nome;
    }

    public Type getTipo() {
        return tipo;
    }

    public String getRole() {
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

    public void setRole(String role) {
        this.role = role;
    }

    public void setTipo(Type tipo) {
        this.tipo = tipo;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
