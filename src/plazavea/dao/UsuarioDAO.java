package plazavea.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import plazavea.config.ConexionOracle;

public class UsuarioDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // VALIDAR LOGIN
    public boolean validarLogin(String username, String password) {

        String sql = "SELECT * FROM usuario WHERE username=? AND password_hash=?";

        try {

            con = ConexionOracle.conectar();
            ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // OBTENER ROL DEL USUARIO
    public String obtenerRol(String username, String password){

        String sql = "SELECT rol FROM usuario WHERE username=? AND password_hash=?";
        String rol = null;

        try{

            con = ConexionOracle.conectar();
            ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();

            if(rs.next()){
                rol = rs.getString("rol");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return rol;
    }
}