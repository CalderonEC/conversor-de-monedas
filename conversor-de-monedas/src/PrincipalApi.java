import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class PrincipalApi {
    public static double convertirMoneda(String monedaOrigen, String monedaDestino, double monto) {
        String direccion = "https://v6.exchangerate-api.com/v6/2782cfb8d59db145f392ea8c/latest/" + monedaOrigen;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            double tipoCambio = parsearTipoCambio(response.body(), monedaDestino);
            if (tipoCambio != -1) {
                double montoConvertido = monto * tipoCambio;
                return montoConvertido;
            } else {
                return -1;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static double parsearTipoCambio(String respuestaJson, String monedaDestino) {
        Gson gson = new Gson();
        Map<String, Object> jsonMap = gson.fromJson(respuestaJson, Map.class);
        Map<String, Double> conversionRates = (Map<String, Double>) jsonMap.get("conversion_rates");

        if (conversionRates != null && conversionRates.containsKey(monedaDestino)) {
            return conversionRates.get(monedaDestino);
        } else {
            System.out.println("Error: El tipo de cambio para la moneda de destino '" + monedaDestino + "' no está disponible.");
            return -1;
        }
    }
}
