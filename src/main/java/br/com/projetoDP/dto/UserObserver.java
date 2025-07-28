package br.com.projetoDP.dto;

import br.com.projetoDP.utils.Type;

public class UserObserver {
    public String nome;
    public String matricula;
    public String email;
    public String role;
    public Type tipo;

    public UserObserver(String nome, String matricula, String email, String role, Type tipo) {
        this.nome = nome;
        this.matricula = matricula;
        this.email = email;
        this.role = role;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Type getTipo() {
        return tipo;
    }

    public void setTipo(Type tipo) {
        this.tipo = tipo;
    }
}
