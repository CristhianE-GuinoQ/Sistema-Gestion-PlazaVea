package plazavea.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import plazavea.config.ConexionOracle;
import plazavea.model.Cliente;

public class ClienteDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // LISTAR CLIENTES
    public List<Cliente> listarClientes() {

        List<Cliente> lista = new ArrayList<>();

        String sql = "SELECT id_cliente, nombres, apellidos, dni, telefono, email FROM cliente";

        try {

            con = ConexionOracle.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                Cliente c = new Cliente();

                c.setIdCliente(rs.getInt("id_cliente"));
                c.setNombres(rs.getString("nombres"));
                c.setApellidos(rs.getString("apellidos"));
                c.setDni(rs.getString("dni"));
                c.setTelefono(rs.getString("telefono"));
                c.setEmail(rs.getString("email"));

                lista.add(c);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }

    // GUARDAR CLIENTE
    public boolean guardarCliente(Cliente c) {

        String sql = "INSERT INTO cliente (id_cliente, dni, nombres, apellidos, telefono, email, fecha_registro, estado) VALUES (?, ?, ?, ?, ?, ?, SYSDATE, 'A')";

        try {

            con = ConexionOracle.conectar();
            ps = con.prepareStatement(sql);

            ps.setInt(1, c.getIdCliente());
            ps.setString(2, c.getDni());
            ps.setString(3, c.getNombres());
            ps.setString(4, c.getApellidos());
            ps.setString(5, c.getTelefono());
            ps.setString(6, c.getEmail());

            ps.executeUpdate();

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    // ACTUALIZAR CLIENTE
    public boolean actualizarCliente(Cliente c) {

        String sql = "UPDATE cliente SET nombres=?, apellidos=?, dni=?, telefono=?, email=? WHERE id_cliente=?";

        try {

            con = ConexionOracle.conectar();
            ps = con.prepareStatement(sql);

            ps.setString(1, c.getNombres());
            ps.setString(2, c.getApellidos());
            ps.setString(3, c.getDni());
            ps.setString(4, c.getTelefono());
            ps.setString(5, c.getEmail());
            ps.setInt(6, c.getIdCliente());

            ps.executeUpdate();

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    // ELIMINAR CLIENTE
    public boolean eliminarCliente(int idCliente) {

        String sql = "DELETE FROM cliente WHERE id_cliente=?";

        try {

            con = ConexionOracle.conectar();
            ps = con.prepareStatement(sql);

            ps.setInt(1, idCliente);

            ps.executeUpdate();

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }

    }

// BUSCAR CLIENTE POR ID
    public Cliente buscarCliente(int idCliente) {

        String sql = "SELECT id_cliente, nombres, apellidos, dni, telefono, email FROM cliente WHERE id_cliente=?";
        Cliente c = null;

        try {

            con = ConexionOracle.conectar();
            ps = con.prepareStatement(sql);

            ps.setInt(1, idCliente);

            rs = ps.executeQuery();

            if (rs.next()) {

                c = new Cliente();

                c.setIdCliente(rs.getInt("id_cliente"));
                c.setNombres(rs.getString("nombres"));
                c.setApellidos(rs.getString("apellidos"));
                c.setDni(rs.getString("dni"));
                c.setTelefono(rs.getString("telefono"));
                c.setEmail(rs.getString("email"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;

    }
}
