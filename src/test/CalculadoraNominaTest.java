package test;

import utils.CalculadoraNomina;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraNominaTest {

    @Test
    public void testCalcularSaludYPension() {
        double salarioBruto = 1000000; // $1.000.000
        // 4% de 1.000.000 = 40.000
        assertEquals(40000, CalculadoraNomina.calcularSalud(salarioBruto), 0.01);
        assertEquals(40000, CalculadoraNomina.calcularPension(salarioBruto), 0.01);
    }

    @Test
    public void testCalcularSalarioNetoCorrecto() {
        double salarioBruto = 2000000;
        // Deducciones obligatorias:
        // Salud (80k) + Pensión (80k) + ARL (approx 10.440) = 170.440
        // Neto esperado sin otros bonos/deducciones: 2.000.000 - 170.440 = 1.829.560
        double netoCalculado = CalculadoraNomina.calcularSalarioNeto(salarioBruto, 0, 0);
        assertEquals(1829560, netoCalculado, 1.0); // Tolerancia por decimales de ARL
    }

    @Test
    public void testValidacionSalarioNetoNegativo() {
        // Forzamos un escenario donde las deducciones superen por completo los ingresos
        assertThrows(ArithmeticException.class, () -> {
            CalculadoraNomina.calcularSalarioNeto(10000, 0, 50000);
        });
    }

    @Test
    public void testConstanteBonoAlimentacion() {
        // Validar que el bono de alimentación cumpla el requisito exacto del PDF
        assertEquals(1000000.0, CalculadoraNomina.BONO_ALIMENTACION, 0.01);
    }

    @Test
    public void testCalcularDeducciones() {
        double bruto = 1000000.0;
        assertEquals(40000, CalculadoraNomina.calcularSalud(bruto), 0.01); // 4%
        assertEquals(40000, CalculadoraNomina.calcularPension(bruto), 0.01); // 4%
        assertEquals(5220, CalculadoraNomina.calcularArl(bruto), 0.01); // 0.522%
    }

    @Test
    public void testSalarioNetoNegativoLanzaExcepcion() {
        // Debe lanzar ArithmeticException si las deducciones superan los ingresos
        assertThrows(ArithmeticException.class, () -> {
            CalculadoraNomina.calcularSalarioNeto(50000, 0, 200000);
        });
    }
}