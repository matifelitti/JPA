package libreria.entidades;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ClienteDAO {

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

    public void guardar(Cliente cliente) {
        conectar();
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        desconectar();
    }

    public void eliminar(Cliente cliente) {
        conectar();
        em.getTransaction().begin();
        if (!em.contains(cliente)) {
            cliente = em.merge(cliente);
        }
        em.remove(cliente);
        em.getTransaction().commit();
        desconectar();
    }

    public void editar(Cliente cliente) {
        conectar();
        em.getTransaction().begin();
        em.merge(cliente);
        em.getTransaction().commit();
        desconectar();
    }

    public List<Cliente> listarTodos() {
        try {
            conectar();
            List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c")
                    .getResultList();
            desconectar();
            return clientes;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Cliente buscarPorId(Integer id) {
        try {
            conectar();
            if (id <= 0) {
                System.out.println("Debe indicar un id mayor a 0");
            }

            Cliente c = (Cliente) em.createQuery("SELECT c FROM Cliente c WHERE c.id = :id").setParameter("id", id).getSingleResult();

            if (c == null) {
                throw new Exception("No se encontró el préstamo");
            }
            desconectar();
            return c;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
}
