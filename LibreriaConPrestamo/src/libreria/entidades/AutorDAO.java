package libreria.entidades;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AutorDAO {

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

    public void guardar(Autor autor) {
        conectar();
        em.getTransaction().begin();
        em.persist(autor);
        em.getTransaction().commit();
        desconectar();
    }

      public void eliminar(Autor autor) {
        conectar();
        em.getTransaction().begin();
        em.remove(autor);
        em.getTransaction().commit();
        desconectar();
    }
    
    public void editar(Autor autor) {
        conectar();
        em.getTransaction().begin();
        em.merge(autor);
        em.getTransaction().commit();
        desconectar();
    }
    
    
    public List<Autor> buscarAutorPorNombre(String autor) {
        try {
            conectar();
            List<Autor> autores = em.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE  :autor")
                    .setParameter("autor", autor).getResultList();
            desconectar();
            return autores;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    
    public List<Autor> listarTodos() {
        try {
            conectar();
            List<Autor> autores = em.createQuery("SELECT a FROM Autor a")
                    .getResultList();
            desconectar();
            return autores;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
