package libreria.servicios;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import libreria.entidades.Cliente;
import libreria.entidades.Libro;
import libreria.entidades.Prestamo;
import libreria.entidades.PrestamoDAO;

public class PrestamoServicio {

    private EditorialServicio editorialservicio;
    private AutorServicio autorservicio;
    private LibroServicio libroservicio;
    private ClienteServicio clienteservicio;
    private final PrestamoDAO DAO;

    public PrestamoServicio() {
        this.DAO = new PrestamoDAO();
    }

    public void setServicios(EditorialServicio editorialservicio, AutorServicio autorservicio, LibroServicio libroservicio, ClienteServicio clienteservicio) {
        this.editorialservicio = editorialservicio;
        this.autorservicio = autorservicio;
        this.libroservicio = libroservicio;
        this.clienteservicio = clienteservicio;
    }

    public Prestamo crearPrestamo(Integer id, Date fechaPrestamo, Date fechaDevolucion, Libro libro, Cliente cliente) throws ParseException {
        Prestamo pr = new Prestamo();
        try {
            pr.setId(id);
            pr.setFechaPrestamo(fechaPrestamo);
            pr.setFechaDevolucion(fechaDevolucion);
            pr.setLibro(libro);
            pr.setCliente(cliente);

            DAO.guardar(pr);
            return pr;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void eliminarPrestamo(Integer id) throws Exception {
        try {
            if (id <= 0) {
                System.out.println("Debe indicar un id mayor a 0");
            }
            Prestamo p = DAO.buscarPorId(id);
            DAO.eliminar(p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void editarPrestamo(Integer id, Date fechaPrestamo, Date fechaDevolucion, Libro libro, Cliente cliente) {
        try {
            if (id <= 0) {
                System.out.println("Debe indicar un id mayor a 0");
            }
            Prestamo p = DAO.buscarPorId(id);
            p.setId(id);
            p.setFechaPrestamo(fechaPrestamo);
            p.setFechaDevolucion(fechaDevolucion);
            p.setLibro(libro);
            p.setCliente(cliente);

            DAO.editar(p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Prestamo> buscarPrestamosporCliente(int id) {
        try {
            if (id <= 0) {
                throw new Exception("El id del cliente deber ser mayor a 0");
            }
            return DAO.buscarPrestamosporCliente(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
        public Prestamo buscarPrestamoporId(int id) {
        try {
            if (id <= 0) {
                throw new Exception("El id del cliente deber ser mayor a 0");
            }
            return DAO.buscarPorId(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    

    public List<Prestamo> listarPrestamos() {
        try {
            return DAO.listarTodos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


}
