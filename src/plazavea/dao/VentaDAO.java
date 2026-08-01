package plazavea.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import plazavea.config.ConexionOracle;
import plazavea.model.Venta;

public class VentaDAO {

    ConexionOracle cn = new ConexionOracle();
    Connection con;
    PreparedStatement ps;

    public int guardarVenta(Venta v) {

        int idVenta = 0;

        String sql = "INSERT INTO VENTA (FECHA_HORA, TOTAL, ID_CLIENTE, ID_USUARIO) VALUES (SYSDATE, ?, ?, ?)";

        try {

            con = ConexionOracle.conectar();
            ps = con.prepareStatement(sql, new String[]{"ID_VENTA"});

            ps.setDouble(1, v.getTotal());
            ps.setInt(2, v.getIdCliente());
            ps.setInt(3, v.getIdUsuario());

            ps.executeUpdate();

            java.sql.ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                idVenta = rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println("Error guardar venta: " + e);
        }

        return idVenta;
    }
}
