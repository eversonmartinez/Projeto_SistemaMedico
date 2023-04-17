package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PessoaTest {
    
    Pessoa pessoa;
    
    @BeforeEach
    void setup(){
        pessoa = new Pessoa("Joao", "91738706095");
    }

    @Test
    void testValidadorCPF() {
        assertThrows(
            IllegalArgumentException.class, 
            () -> new Pessoa("123456789", "Maria" ));
    }


}
