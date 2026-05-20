package interfaces;

/**
 * Interfaz para empleados que legalmente reciben beneficios adicionales
 * (Bono de alimentación, comisiones especiales, etc.) .
 */
public interface Beneficiable {
    /**
     * Calcula la sumatoria de beneficios extra que apliquen al empleado.
     */
    double calcularBeneficios();
}