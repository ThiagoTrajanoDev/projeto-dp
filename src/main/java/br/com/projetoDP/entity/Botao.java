package br.com.projetoDP.entity;


import br.com.projetoDP.utils.Model;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class Botao extends Model {

    @Column(nullable = false)
    public String local;

    void setLocal(String local) {
        this.local = local;
    }

    public String getLocal() {
        return local;
    }
}
