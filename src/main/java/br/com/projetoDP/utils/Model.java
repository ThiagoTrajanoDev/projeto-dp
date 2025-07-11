package br.com.projetoDP.utils;


import jakarta.persistence.*;

@MappedSuperclass
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
}
