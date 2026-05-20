package interfaces;

 //Interfaz que define el contrato financiero para el sistema de nómina.
public interface Calculable {
    //Calcula el dinero bruto que devenga el empleado antes de descuentos.
    double calcularSalario();

     double calcularDeducciones();

     double calcularBeneficios();

     double calcularSalarioNeto();
}