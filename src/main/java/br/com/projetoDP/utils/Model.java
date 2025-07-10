package br.com.projetoDP.utils;


import jakarta.persistence.*;

@MappedSuperclass
@Access(AccessType.PROPERTY)
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
