package libreria.servicios;

import java.util.List;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.entidades.LibroDAO;

public class LibroServicio {

    private EditorialServicio editorialservicio;
    private AutorServicio autorservicio;
    private final LibroDAO DAO;

    public LibroServicio() {
        this.DAO = new LibroDAO();
    }

    public void setServicios(EditorialServicio editorialservicio, AutorServicio autorservicio) {
        this.editorialservicio = editorialservicio;
        this.autorservicio = autorservicio;
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

}
