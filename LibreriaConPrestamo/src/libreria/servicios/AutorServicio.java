package libreria.servicios;

import java.util.List;
import libreria.entidades.Autor;
import libreria.entidades.AutorDAO;

public class AutorServicio {

    private EditorialServicio editorialservicio;
    private LibroServicio libroservicio;
    private PrestamoServicio prestamoservicio;
    private ClienteServicio clienteservicio;
    private final AutorDAO DAO;

    public AutorServicio() {
        this.DAO = new AutorDAO();
    }

    public void setServicios(EditorialServicio editorialservicio, LibroServicio libroservicio, PrestamoServicio prestamoservicio, ClienteServicio clienteservicio) {
        this.editorialservicio = editorialservicio;
        this.libroservicio = libroservicio;
        this.prestamoservicio = prestamoservicio;
        this.clienteservicio = clienteservicio;
    }

    public Autor crearAutor(Integer id, String nombre, Boolean alta) {

        Autor aut = new Autor();
        try {
            aut.setId(id);
            aut.setNombre(nombre);
            aut.setAlta(alta);

            DAO.guardar(aut);
            return aut;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Autor> buscarAutorPorNombre(String nombre) {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar un nombre de autor");
            }
            return DAO.buscarAutorPorNombre(nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Autor> listarAutores() {
        try {
            return DAO.listarTodos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
