package plazavea.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import plazavea.config.ConexionOracle;
import plazavea.model.DetalleVenta;

public class DetalleVentaDAO {

    ConexionOracle cn = new ConexionOracle();
    Connection con;
    PreparedStatement ps;

    public boolean guardarDetalle(DetalleVenta d) {

        String sql = "INSERT INTO DETALLE_VENTA (ID_VENTA, ID_PRODUCTO, CANTIDAD, PRECIO_UNITARIO, SUBTOTAL) VALUES (?,?,?,?,?)";

        try {

            con = ConexionOracle.conectar();
            ps = con.prepareStatement(sql);

            ps.setInt(1, d.getIdVenta());
            ps.setInt(2, d.getIdProducto());
            ps.setInt(3, d.getCantidad());
            ps.setDouble(4, d.getPrecio());
            ps.setDouble(5, d.getPrecio() * d.getCantidad());

            ps.executeUpdate();

            return true;

        } catch (Exception e) {

            System.out.println("Error guardar detalle venta: " + e);
            return false;

        }

    }

}
