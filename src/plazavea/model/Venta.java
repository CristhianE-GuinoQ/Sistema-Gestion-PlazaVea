package plazavea.model;

public class Venta {

    private int idVenta;
    private String fechaHora;
    private double total;
    private int idCliente;
    private int idUsuario;

    public Venta() {
    }

    public Venta(int idVenta, String fechaHora, double total, int idCliente, int idUsuario) {
        this.idVenta = idVenta;
        this.fechaHora = fechaHora;
        this.total = total;
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}