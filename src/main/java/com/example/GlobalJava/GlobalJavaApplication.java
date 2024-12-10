package com.example.GlobalJava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class GlobalJavaApplication {

	public static Connection conectarBD(){
		Connection conexion;
		String host = "jdbc:mysql://localhost:3306/prueba_bd";
		String user = "root";
		String password = "";
		String bd = "prueba_bd";

		System.out.println("Conectandoo....");

		try {
			conexion = DriverManager.getConnection(host, user , password);
			System.out.println("Conexion exitosa!!!");
		} catch (SQLException e){
			System.out.println("Error no se pudo conectar" + e.getMessage());
			throw new RuntimeException(e);
		}
		return conexion;

	}
	public static void desconexion (Connection cb){
        try {
            cb.close();
			System.out.println("desconectado");
        } catch (SQLException e) {
			System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

	public static void main(String[] args) {
		SpringApplication.run(GlobalJavaApplication.class, args);
		Connection bd = conectarBD();
		System.out.println("consultas terminadas");
		desconexion(bd);
	}

}
