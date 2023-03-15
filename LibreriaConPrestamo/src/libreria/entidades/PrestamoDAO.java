package libreria.entidades;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PrestamoDAO {

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

    public void guardar(Prestamo prestamo) {
        conectar();
        em.getTransaction().begin();
        em.persist(prestamo);
        em.getTransaction().commit();
        desconectar();
    }

    public void eliminar(Prestamo prestamo) {
        conectar();
        em.getTransaction().begin();
        if (!em.contains(prestamo)) {
            prestamo = em.merge(prestamo);
        }
        em.remove(prestamo);
        em.getTransaction().commit();
        desconectar();
    }

    public void editar(Prestamo prestamo) {
        conectar();
        em.getTransaction().begin();
        em.merge(prestamo);
        em.getTransaction().commit();
        desconectar();
    }

    public List<Prestamo> listarTodos() {
        try {
            conectar();
            List<Prestamo> prestamos = em.createQuery("SELECT p FROM Prestamo p")
                    .getResultList();
            desconectar();
            return prestamos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Prestamo buscarPorId(Integer id) {
        try {
            conectar();
            if (id <= 0) {
                System.out.println("Debe indicar un id mayor a 0");
            }

            Prestamo presta = (Prestamo) em.createQuery("SELECT p FROM Prestamo p WHERE p.id = :id").setParameter("id", id).getSingleResult();

            if (presta == null) {
                throw new Exception("No se encontró el préstamo");
            }
            desconectar();
            return presta;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public List<Prestamo> buscarPrestamosporCliente(int id) {
        try {
            conectar();
            List<Prestamo> pr = em.createQuery("SELECT p FROM Prestamo p WHERE p.cliente.id = :id")
                    .setParameter("id", id).getResultList();
            desconectar();
            return pr;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    

}
