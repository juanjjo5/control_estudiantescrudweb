package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDaoJDBC  {
    
    private static final String SQL_SELECT
            = "SELECT id_estudiante,nombre,apellido,email,edad FROM estudiante";
    private static final String SQL_SELECT_BY_ID
            = "SELECT id_estudiante nombre,apellido,email,telefono,edad from estudiante where id=?";
    private static final String SQL_INSERT
            = "INSERT into estudiante(nombre,apellido,email,telefono,edad) values(?,?,?,?,?)";
    private static final String SQL_UPDATE
            = "UPDATE estudiante SET nombre=?,apellido=?,email=?,telefono=?,edad=? where id_estudiante=?";
    private static final String SQL_DELETE
            = "DELETE FROM estudiante where id_estudiante=?";
    
    Connection conn;
    
    public List<Estudiante> listar() {
        List<Estudiante> estudiantes = new ArrayList<>();
        try
            (Connection conn=Conexion.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(SQL_SELECT);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idEstudiante = rs.getInt("id_estudiante");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                int edad = rs.getInt("edad");
                
                Estudiante estudiante = new Estudiante(idEstudiante, nombre, apellido, email, telefono, edad);
                estudiantes.add(estudiante);
                
            }
            
        } catch (Exception e) {
            System.out.println("error al listar estudiantes"+e.getMessage());
            
        }
        return estudiantes;
    }
    
    
    
    
}
