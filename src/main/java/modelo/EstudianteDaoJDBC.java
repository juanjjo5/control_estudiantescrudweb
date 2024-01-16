package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDaoJDBC {

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

    public List<Estudiante> listar() {
        List<Estudiante> estudiantes = new ArrayList<>();
        try (Connection conn = Conexion.getConnection()) {
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
            System.out.println("error al listar estudiantes" + e.getMessage());

        }
        return estudiantes;
    }

    public Estudiante buscar(Estudiante estudiante) {
        try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_ID)) {
            stmt.setInt(1, estudiante.idEstudiante);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    String email = rs.getString("email");
                    String telefono = rs.getString("telefoo");
                    int edad = rs.getInt("edad");
                    estudiante.setNombre(nombre);
                    estudiante.setApellido(apellido);
                    estudiante.setEmail(email);
                    estudiante.setTelefono(telefono);
                    estudiante.setEdad(edad);

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return estudiante;

    }

    public int insertar(Estudiante estudiante) {
        int row = 0;
        try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {
            stmt.setString(1, estudiante.getNombre());
            stmt.setString(1, estudiante.getApellido());
            stmt.setString(1, estudiante.getEmail());
            stmt.setString(1, estudiante.getTelefono());
            stmt.setInt(1, estudiante.getEdad());
            row=stmt.executeUpdate();

        }catch(Exception e){
            System.out.println("no se puede insertar"+e);
        }
        return row;

    }
    public int actualizar(Estudiante estudiante ){
        int row=0;
        try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE)){
            stmt.setString(1, estudiante.getNombre());
            stmt.setString(2, estudiante.getApellido());
            stmt.setString(3, estudiante.getEmail());
            stmt.setString(4, estudiante.getTelefono());
            stmt.setInt(5, estudiante.getEdad());
            
            row=stmt.executeUpdate();
    }catch(Exception e){
            System.out.println("error en el update"+e);
        
    }
    return row;
    }
    public int eliminar(Estudiante estudiante){
    int row=0;
    try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_DELETE)){
    stmt.setInt(1, estudiante.idEstudiante);
    row=stmt.executeUpdate();
    }catch(Exception e){
        System.out.println("error al eliminar"+e);
    }
    return row;
    
    }
    
    }
    
    
   
    
