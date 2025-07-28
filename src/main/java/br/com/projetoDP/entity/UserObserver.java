package br.com.projetoDP.entity;

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
    @Enumerated(EnumType.STRING)
    public Role role;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public Type tipo;
}
