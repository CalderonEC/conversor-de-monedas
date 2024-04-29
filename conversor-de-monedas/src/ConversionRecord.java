import java.time.LocalDateTime;

public class ConversionRecord {
    private LocalDateTime timestamp;
    private double monto;
    private String monedaOrigen;
    private double montoConvertido;
    private String monedaDestino;

    public ConversionRecord(LocalDateTime timestamp, double monto, String monedaOrigen, double montoConvertido, String monedaDestino) {
        this.timestamp = timestamp;
        this.monto = monto;
        this.monedaOrigen = monedaOrigen;
        this.montoConvertido = montoConvertido;
        this.monedaDestino = monedaDestino;
    }

    @Override
    public String toString() {
        return "Fecha y Hora: " + timestamp + ", Monto Original: " + monto + " " + monedaOrigen + ", Monto Convertido: " + montoConvertido + " " + monedaDestino;
    }
}
