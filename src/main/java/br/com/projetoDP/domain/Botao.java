package br.com.projetoDP.domain;


import br.com.projetoDP.utils.Model;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class Botao extends Model {
    public String local;
    public LocalDateTime horario;

    void setLocal(String local) {
        this.local = local;
    }

    public String getLocal() {
        return local;
    }

    void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public LocalDateTime getHorario() {
        return horario;
    }
}
