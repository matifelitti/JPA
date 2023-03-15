package libreria.servicios;

import java.util.List;
import libreria.entidades.Editorial;
import libreria.entidades.EditorialDAO;
public class EditorialServicio {

    private AutorServicio autorservicio;
    private LibroServicio libroservicio;
    private PrestamoServicio prestamoservicio;
    private ClienteServicio clienteservicio;
    private final EditorialDAO DAO;

    public EditorialServicio() {
        this.DAO = new EditorialDAO();
    }

    public void setServicios(AutorServicio autorservicio, LibroServicio libroservicio, PrestamoServicio prestamoservicio, ClienteServicio clienteservicio) {
        this.autorservicio = autorservicio;
        this.libroservicio = libroservicio;
        this.prestamoservicio = prestamoservicio;
        this.clienteservicio = clienteservicio;
    }

    public Editorial crearEditorial(Integer id, String nombre, Boolean alta) {

        Editorial edi = new Editorial();
        try {
            edi.setId(id);
            edi.setNombre(nombre);
            edi.setAlta(alta);

            DAO.guardar(edi);
            return edi;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
        public List<Editorial> listareditoriales() {
        try {
            return DAO.listarTodos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
