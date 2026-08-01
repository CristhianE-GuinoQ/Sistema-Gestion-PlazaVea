package plazavea.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionOracle {

    public static Connection conectar(){

        Connection con = null;

        try{

            Class.forName("oracle.jdbc.driver.OracleDriver");

            con = DriverManager.getConnection(
            "jdbc:oracle:thin:@localhost:1521/XEPDB1",
            "plazavea",
            "plazavea123");

            System.out.println("Conexion exitosa");

        }catch(Exception e){

            System.out.println("Error conexion: " + e);

        }

        return con;
    }

}