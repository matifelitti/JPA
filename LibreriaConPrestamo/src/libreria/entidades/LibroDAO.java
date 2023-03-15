package libreria.entidades;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class LibroDAO {

    private final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("persist");
    private EntityManager em = EMF.createEntityManager();

    public void conectar() {
        if (!em.isOpen()) {
            em = EMF.createEntityManager();
        }
    }

    public void desconectar() {
        if (em.isOpen()) {
            em.close();
        }
    }

    public void guardar(Libro libro) {
        conectar();
        em.getTransaction().begin();
        em.persist(libro);
        em.getTransaction().commit();
        desconectar();
    }

    public void eliminar(Libro libro) {
        conectar();
        em.getTransaction().begin();
        em.remove(libro);
        em.getTransaction().commit();
        desconectar();
    }

    public void editar(Libro libro) {
        conectar();
        em.getTransaction().begin();
        em.merge(libro);
        em.getTransaction().commit();
        desconectar();
    }

    public List<Libro> buscarPorISBN(long isbn) {
        try {
            conectar();
            List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.isbn = :isbn")
                    .setParameter("isbn", isbn).getResultList();
            desconectar();
            return libros;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Libro> buscarPorTitulo(String titulo) {
        try {
            conectar();
            List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE  :titulo")
                    .setParameter("titulo", titulo).getResultList();
            desconectar();
            return libros;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Libro> buscarLibroPorNombreAutor(String nombre) {
        try {
            conectar();
            List<Libro> libros = em.createQuery("SELECT l FROM Libro l JOIN l.autor a WHERE a.nombre LIKE :nombre")
                    .setParameter("nombre", nombre).getResultList();
            desconectar();
            return libros;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Libro> buscarLibroPorEditorial(String nombre) {
        try {
            conectar();
            List<Libro> li = em.createQuery("SELECT l FROM Libro l JOIN l.editorial e WHERE e.nombre LIKE :nombre")
                    .setParameter("nombre", nombre).getResultList();
            desconectar();
            return li;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public List<Libro> listarTodos() {
        try {
            conectar();
            List<Libro> libros = em.createQuery("SELECT l FROM Libro l")
                    .getResultList();
            desconectar();
            return libros;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
