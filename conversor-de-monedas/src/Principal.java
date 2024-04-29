import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private static List<ConversionRecord> historial = new ArrayList<>();

    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n**************************************************");
            System.out.println("Sea bienvenido/a al conversor de Moneda Alura :)");
            System.out.println("**************************************************");

            mostrarMenu();

            opcion = lectura.nextInt();
            switch (opcion) {
                case 1:
                    convertirMoneda("USD", "ARS");
                    break;
                case 2:
                    convertirMoneda("ARS", "USD");
                    break;
                case 3:
                    convertirMoneda("USD", "BRL");
                    break;
                case 4:
                    convertirMoneda("BRL", "USD");
                    break;
                case 5:
                    convertirMoneda("USD", "COP");
                    break;
                case 6:
                    convertirMoneda("COP", "USD");
                    break;
                case 7:
                    mostrarHistorial();
                    break;
                case 9:
                    System.out.println("Saliendo de la aplicación");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida del menú.");
            }

        } while (opcion != 9);
    }


    private static void mostrarMenu() {
        System.out.println("""
                *** Escriba el número de opción deseada ***
                                
                1- Dólar (USD) => Peso Argentino (ARS)
                2- Peso Argentino (ARS) => Dólar (USD)
                3- Dólar (USD) => Real Brasileño (BRL)
                4- Real Brasileño (BRL) => Dólar (USD)
                5- Dólar (USD) => Peso Colombiano (COP)
                6- Peso Colombiano (COP) => Dólar (USD)
                7- Historial de Conversiones realizadas
                9- Salir
                Elija una opción:""");
    }

    private static void convertirMoneda(String monedaOrigen, String monedaDestino) {
        Scanner lectura = new Scanner(System.in);
        System.out.println("Ingrese el monto a convertir en " + monedaOrigen + ":");
        double monto = lectura.nextDouble();

        double montoConvertido = PrincipalApi.convertirMoneda(monedaOrigen, monedaDestino, monto);
        if (montoConvertido >= 0) {
            System.out.println("El monto convertido a " + monedaDestino + " es: " + montoConvertido);
            historial.add(new ConversionRecord(LocalDateTime.now(), monto, monedaOrigen, montoConvertido, monedaDestino));
        } else {
            // Si el monto convertido es -1.0, indica que el tipo de cambio no está disponible
            if (montoConvertido == -1.0) {
                System.out.println("Error: El tipo de cambio para la moneda de destino '" + monedaDestino + "' no está disponible.");
            } else {
                System.out.println("Error inesperado.");
            }
        }

        System.out.println("¿Desea realizar otra operación?");
        System.out.println("1- Sí");
        System.out.println("2- No");
        System.out.print("Elija una opción: ");
        int opcion = lectura.nextInt();
        if (opcion == 2) {
            System.out.println("Saliendo de la aplicación");
            System.exit(0);
        }
    }

    private static void mostrarHistorial() {
        if (historial.isEmpty()) {
            System.out.println("Error: Aún no ha realizado ninguna conversión.");
        } else {
            System.out.println("Historial de Conversiones:");
            for (ConversionRecord registro : historial) {
                System.out.println(registro);
            }

        }
    }
}