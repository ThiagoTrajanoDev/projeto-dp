package br.com.projetoDP.entity;


import br.com.projetoDP.utils.Model;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class Botao extends Model {
    private String local;

    void setLocal(String local) {
        this.local = local;
    }

    public String getLocal() {
        return local;
    }
}
