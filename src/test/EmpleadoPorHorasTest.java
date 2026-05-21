package test;
 import model.EmpleadoPorHoras;
 import org.junit.jupiter.api.Test;

 import static org.junit.jupiter.api.Assertions.*;

public class EmpleadoPorHorasTest {

    @Test
    void testMenos40HorasTodoNormal() {
        EmpleadoPorHoras e = new EmpleadoPorHoras("Ana", "001", 10000, 35);
        assertEquals(35, e.horasOrdinarias(), 0.01);
        assertEquals(0,  e.horasExtras(), 0.01);
        assertEquals(350_000.0, e.calcularSalario(), 0.01);
    }

    @Test
    void testExactamente40HorasSinExtras() {
        EmpleadoPorHoras e = new EmpleadoPorHoras("Ana", "001", 10000, 40);
        assertEquals(0, e.horasExtras(), 0.01);
        assertEquals(400_000.0, e.calcularSalario(), 0.01);
    }

    @Test
    void test50HorasConExtras() {

        EmpleadoPorHoras e = new EmpleadoPorHoras("Carlos", "002", 10000, 50);
        assertEquals(40, e.horasOrdinarias(), 0.01);
        assertEquals(10, e.horasExtras(),   0.01);
        assertEquals(550_000.0, e.calcularSalario(), 0.01);
    }

    // ─── Beneficios y deducciones ────────────────────────────────────

    @Test
    void testNoBeneficios() {
        EmpleadoPorHoras e = new EmpleadoPorHoras("Luis", "003", 20000, 40);
        assertEquals(0.0, e.calcularBeneficios(), 0.01);
    }

    @Test
    void testDeduccionesCorrectas() {
        EmpleadoPorHoras e = new EmpleadoPorHoras("Luis", "003", 20000, 40);
        double bruto = 800_000.0;
        double esperado = (bruto * 0.04) + (bruto * 0.04) + (bruto * 0.00522);
        assertEquals(esperado, e.calcularDeducciones(), 0.01);
    }

    @Test
    void testSalarioNetoEsMenorQueBruto() {
        EmpleadoPorHoras e = new EmpleadoPorHoras("Luis", "003", 20000, 40);
        assertTrue(e.calcularSalarioNeto() < e.calcularSalario());
    }

    // ─── Validaciones ────────────────────────────────────────────────

    @Test
    void testHorasNegativasLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () ->
                new EmpleadoPorHoras("X", "000", 10000, -1)
        );
    }

    @Test
    void testTarifaCeroLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () ->
                new EmpleadoPorHoras("X", "000", 0, 40)
        );
    }

    @Test
    void testTarifaNegativaLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () ->
                new EmpleadoPorHoras("X", "000", -5000, 40)
        );
    }
}
