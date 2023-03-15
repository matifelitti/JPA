package libreria.servicios;

import java.util.List;
import libreria.entidades.Cliente;
import libreria.entidades.ClienteDAO;

public class ClienteServicio {

    private EditorialServicio editorialservicio;
    private AutorServicio autorservicio;
    private LibroServicio libroservicio;
    private PrestamoServicio prestamoservicio;
    private final ClienteDAO DAO;

    public ClienteServicio() {
        this.DAO = new ClienteDAO();
    }

    public void setServicios(EditorialServicio editorialservicio, AutorServicio autorservicio, LibroServicio libroservicio, PrestamoServicio prestamoservicio) {
        this.editorialservicio = editorialservicio;
        this.autorservicio = autorservicio;
        this.libroservicio = libroservicio;
        this.prestamoservicio = prestamoservicio;
    }

    public Cliente crearCliente(Integer id, Long dni, String nombre, String apellido, String telefono) {

        Cliente cl = new Cliente();
        try {
            cl.setId(id);
            cl.setDni(dni);
            cl.setNombre(nombre);
            cl.setApellido(apellido);
            cl.setTelefono(telefono);

            DAO.guardar(cl);
            return cl;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void eliminarCliente(Integer id) throws Exception {
        try {
            if (id <= 0) {
                System.out.println("Debe indicar un id mayor a 0");
            }
            Cliente c = DAO.buscarPorId(id);
            DAO.eliminar(c);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void editarCliente(Integer id, Long dni, String nombre, String apellido, String telefono) {
        try {
            if (id <= 0) {
                System.out.println("Debe indicar un id mayor a 0");
            }
            Cliente c = DAO.buscarPorId(id);
            c.setId(id);
            c.setDni(dni);
            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setTelefono(telefono);

            DAO.editar(c);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Cliente> listarClientes() {
        try {
            return DAO.listarTodos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
