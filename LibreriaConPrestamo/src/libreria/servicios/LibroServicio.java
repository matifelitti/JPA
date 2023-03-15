package libreria.servicios;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.entidades.LibroDAO;
import libreria.entidades.Prestamo;
import libreria.entidades.PrestamoDAO;

public class LibroServicio {

    private EditorialServicio editorialservicio;
    private AutorServicio autorservicio;
    private PrestamoServicio prestamoservicio;
    private ClienteServicio clienteservicio;
    private final LibroDAO DAO;
    private final PrestamoDAO DAOP;

    public LibroServicio() {
        this.DAO = new LibroDAO();
        this.DAOP = new PrestamoDAO();
    }

    public void setServicios(EditorialServicio editorialservicio, AutorServicio autorservicio, PrestamoServicio prestamoservicio, ClienteServicio clienteservicio) {
        this.editorialservicio = editorialservicio;
        this.autorservicio = autorservicio;
        this.prestamoservicio = prestamoservicio;
        this.clienteservicio = clienteservicio;
    }

    public Libro crearLibro(long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Boolean alta, Autor autor, Editorial editorial) {

        Libro lib = new Libro();
        try {
            lib.setIsbn(isbn);
            lib.setTitulo(titulo);
            lib.setAnio(anio);
            lib.setEjemplares(ejemplares);
            lib.setEjemplaresPrestados(ejemplaresPrestados);
            lib.setEjemplaresRestantes(ejemplaresRestantes);
            lib.setAlta(alta);
            lib.setAutor(autor);
            lib.setEditorial(editorial);

            DAO.guardar(lib);
            return lib;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Libro> buscarPorISBN(long isbn) {

        try {
            if (isbn < 0) {
                throw new Exception("Debe indicar un ISBN mayor a 0");
            }
            return DAO.buscarPorISBN(isbn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Libro> buscarPorTitulo(String titulo) {
        try {
            if (titulo == null || titulo.trim().isEmpty()) {
                throw new Exception("Debe indicar un titulo");
            }
            return DAO.buscarPorTitulo(titulo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Libro> buscarLibroPorNombreAutor(String nombre) {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar un nombre de autor");
            }
            return DAO.buscarLibroPorNombreAutor(nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Libro> buscarLibroPorEditorial(String nombre) {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar un nombre de editorial");
            }
            return DAO.buscarLibroPorEditorial(nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Libro> listarLibros() {
        try {
            return DAO.listarTodos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Libro logicaPrestamo(Libro libro, Prestamo prestamo, Integer id) throws ParseException, Exception {
        try {
            if (Objects.equals(libro.getEjemplares(), libro.getEjemplaresPrestados())) {
                System.out.println("Todos los ejemplares de ese libro ya se encuentran prestados, el Prestamo no se tendrá en cuenta");
                prestamoservicio.crearPrestamo(prestamo.getId(), prestamo.getFechaPrestamo(), prestamo.getFechaDevolucion(), prestamo.getLibro(), prestamo.getCliente());
                prestamoservicio.eliminarPrestamo(id);
            }

            if (!Objects.equals(libro.getEjemplares(), libro.getEjemplaresPrestados())) {
                libro.setEjemplaresPrestados(libro.getEjemplaresPrestados() + 1);
                libro.setEjemplaresRestantes(libro.getEjemplaresRestantes() - 1);
                DAO.editar(libro);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

        return libro;
    }

    public Libro logicaDevoluvion(Libro libro, Prestamo prestamo, int id) throws Exception {
        if (Objects.equals(libro.getEjemplares(), libro.getEjemplaresRestantes())) {
            System.out.println("Todos los ejemplares de ese libro ya se encuentran devueltos");
            prestamoservicio.eliminarPrestamo(id);
        }

        if (!Objects.equals(libro.getEjemplares(), libro.getEjemplaresRestantes())) {
            libro.setEjemplaresPrestados(libro.getEjemplaresPrestados() - 1);
            libro.setEjemplaresRestantes(libro.getEjemplaresRestantes() + 1);
            prestamoservicio.eliminarPrestamo(id);
            DAO.editar(libro);
        }
        return libro;
    }

}
