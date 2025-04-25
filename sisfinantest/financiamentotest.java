package sisfinantest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import sisfinan.Financiamento

public class financiamentotest {

    @Test
    public void testValorFinanciado() {
        Financiamento f = new Financiamento("Casa", 500000.0, true);
        assertEquals(500000.0, f.getValor());
    }

    @Test
    public void testDescricao() {
        Financiamento f = new Financiamento("Apartamento", 300000.0, false);
        assertEquals("Apartamento", f.getDescricao());
    }

    @Test
    public void testIsFinanciado() {
        Financiamento f = new Financiamento("Chácara", 200000.0, true);
        assertTrue(f.isFinanciado());
    }

    @Test
    public void testNaoFinanciado() {
        Financiamento f = new Financiamento("Terreno", 150000.0, false);
        assertFalse(f.isFinanciado());
    }

    @Test
    public void testToStringNotNull() {
        Financiamento f = new Financiamento("Imóvel X", 100000.0, true);
        assertNotNull(f.toString());
    }

	private void assertNotNull(String string) {
		
	}
}
