package plazavea.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import plazavea.config.ConexionOracle;
import plazavea.model.Producto;

public class ProductoDAO {

    ConexionOracle cn = new ConexionOracle();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean guardarProducto(Producto p) {

        String sql = "INSERT INTO PRODUCTO (ID_PRODUCTO, NOMBRE, MARCA, PRECIO, STOCK, ESTADO) VALUES (?,?,?,?,?,?)";

        try {

            con = ConexionOracle.conectar();
            ps = con.prepareStatement(sql);

            ps.setInt(1, p.getIdProducto());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getMarca());
            ps.setDouble(4, p.getPrecio());
            ps.setInt(5, p.getStock());
            ps.setString(6, p.getEstado());

            ps.executeUpdate();

            return true;

        } catch (Exception e) {

            System.out.println("Error al guardar producto: " + e);
            return false;

        }

    }

    public void eliminarProducto(int id) {

        String sql = "DELETE FROM PRODUCTO WHERE ID_PRODUCTO=?";

        try {

            con = ConexionOracle.conectar();
            ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {

            System.out.println("Error eliminar producto: " + e);

        }

    }

    public Producto buscarProducto(int id) {

        Producto p = new Producto();

        String sql = "SELECT * FROM PRODUCTO WHERE ID_PRODUCTO=?";

        try {

            con = ConexionOracle.conectar();
            ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {

                p.setIdProducto(rs.getInt("ID_PRODUCTO"));
                p.setNombre(rs.getString("NOMBRE"));
                p.setMarca(rs.getString("MARCA"));
                p.setPrecio(rs.getDouble("PRECIO"));
                p.setStock(rs.getInt("STOCK"));

            }

        } catch (Exception e) {

            System.out.println("Error buscar producto: " + e);

        }

        return p;

    }

  public void actualizarProducto(Producto p) {

    String sql = "UPDATE PRODUCTO SET NOMBRE=?, MARCA=?, PRECIO=?, STOCK=? WHERE ID_PRODUCTO=?";

    try {

        con = ConexionOracle.conectar();
        ps = con.prepareStatement(sql);

        ps.setString(1, p.getNombre());
        ps.setString(2, p.getMarca());
        ps.setDouble(3, p.getPrecio());
        ps.setInt(4, p.getStock());
        ps.setInt(5, p.getIdProducto());

        ps.executeUpdate();

    } catch (Exception e) {

        System.out.println("Error actualizar producto: " + e);

    }

}

public ResultSet listarProductos(){

    String sql = "SELECT * FROM PRODUCTO";

    try{

        con = ConexionOracle.conectar();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

    }catch(Exception e){

        System.out.println("Error listar productos: " + e);

    }

    return rs;

}

}