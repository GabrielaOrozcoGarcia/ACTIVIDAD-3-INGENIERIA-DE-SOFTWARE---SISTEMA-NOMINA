package model;
import utils.CalculadoraNomina;
import utils.Constantes;
public class EmpleadoPorHoras extends Empleado{

    protected double valor_hora;
    protected double horas_trabajadas;


    public EmpleadoPorHoras(String nombre, String identificacion, double valor_hora,  double horas_trabajadas){
        super(nombre, identificacion, 0, 0);
        validarHoras(horas_trabajadas, "Horas Trabajadas");
        validarValorHora(valor_hora);

        this.valor_hora = valor_hora;
        this.horas_trabajadas=horas_trabajadas;
    }

    // Valiadioces de datos

    private void validarHoras(double horas, String campo){
        if (horas < 0){
            throw new IllegalArgumentException("Las " + campo + "no puede ser negativo");
        }
    }

    private void validarValorHora (double valor) {
        if (valor <=  0){
            throw new IllegalArgumentException("El vaor de las horas debe ser mayor a 0");
        }
    }

    // Calculos para las horas

    public double horasOrdinarias(){
        return Math.min(horas_trabajadas , Constantes.HORAS_ORDINARIAS);
    }

    public double horasExtras(){
        return Math.max(horas_trabajadas - Constantes.HORAS_ORDINARIAS, 0);
    }

    // implrmrntacion de metodos empleados
    @Override
    public double calcularSalario(){
        double pagoOrdinario= horasOrdinarias()*valor_hora;
        double pagoExtra = horasExtras() * valor_hora * Constantes.RECARGO_HORA;
        return pagoExtra + pagoOrdinario;
    }

    @Override
    public double calcularDeducciones(){
        double pago  = calcularSalario();
        return CalculadoraNomina.calcularSalud(pago) +
                CalculadoraNomina.calcularPension(pago) +
                CalculadoraNomina.calcularArl(pago);
    }

    @Override
    public double calcularBeneficios(){
        return 0;
    }

    @Override
    public String toString() {
        return String.format(
                "=== Empleado por Horas ===\n" +
                        "Nombre          : %s\n" +
                        "Identificación  : %s\n" +
                        "Horas trabajadas: %.1f (normales: %.1f | extras: %.1f)\n" +
                        "Valor/hora     : $%,.2f\n" +
                        "Salario bruto   : $%,.2f\n" +
                        "Deducciones     : $%,.2f\n" +
                        "Beneficios      : $%,.2f\n" +
                        "Salario neto    : $%,.2f",
                nombre, identificacion,
                horas_trabajadas, horasOrdinarias(), horasExtras(), valor_hora,
                calcularSalario(),
                calcularDeducciones(),
                calcularBeneficios(),
                calcularSalarioNeto()
        );
    }

    public double getHoras_trabajadas() {
        return horas_trabajadas;
    }

    public double getValor_hora() {
        return valor_hora;
    }
}
