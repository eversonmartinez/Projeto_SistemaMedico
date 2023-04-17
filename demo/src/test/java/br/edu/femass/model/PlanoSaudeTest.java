package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlanoSaudeTest {

    PlanoSaude planoSaude;
    
    @BeforeEach
    void setup(){
        planoSaude = new PlanoSaude("Unimed");
    }

    @Test
    void testEquals() {
         PlanoSaude planoSaude2 = new PlanoSaude("Unimed");
         assertEquals(true, planoSaude2.equals(planoSaude));
    }

    @Test
    void testGetAtivo() {
        assertEquals(true, planoSaude.getAtivo());
    }

    @Test
    void testGetnome() {
        assertEquals("Unimed", planoSaude.getNome());
    }

    @Test
    void testSetAtivo() {
        planoSaude.setAtivo(false);
        assertEquals(false, planoSaude.getAtivo());
    }

    @Test
    void testToString() {

    }
}
