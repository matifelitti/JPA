package libreria;


import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.servicios.AutorServicio;
import libreria.servicios.EditorialServicio;
import libreria.servicios.LibroServicio;

public class menu {
    
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    
    private final LibroServicio ls = new LibroServicio();
    private final AutorServicio as = new AutorServicio();
    private final EditorialServicio es = new EditorialServicio();

    public menu() {
        ls.setServicios(es, as);
        as.setServicios(es, ls);
        es.setServicios(as, ls);
    }

    
    public void ejecucion() {
        
        //-----------------------AUTORES-------------------------------//

        Autor a1 = as.crearAutor(1,"Dan Brown",true);
        Autor a2 = as.crearAutor(2,"Sun Tzu",false);
        Autor a3 = as.crearAutor(3, "Dante Alighieri", false);
        Autor a4 = as.crearAutor(4, "Suzanne Collins", true);
        Autor a5 = as.crearAutor(5, "Nicolas Maquiavelo", true);
        Autor a6 = as.crearAutor(6, "J K Rowlin", false);
        Autor a7 = as.crearAutor(7, "J R R Tolkien", true);


        //-------------------------EDITORIALES---------------------------//

        Editorial e1 = es.crearEditorial(1, "Santillana", true);
        Editorial e2 = es.crearEditorial(2, "PenguinRandomHouse", false);
        Editorial e3 = es.crearEditorial(3, "Planeta", true);
        Editorial e4 = es.crearEditorial(4, "Salamandra", false);
        Editorial e5 = es.crearEditorial(5, "Ediciones Minotauro", true);


        //-----------------------------LIBROS-------------------------------//

        Libro l1 = ls.crearLibro(1, "El codigo Da Vinci", 2005, 5, 2, 3, true, a1, e3);
        Libro l2 = ls.crearLibro(2, "La Divina Comedia", 1450, 4, 1, 3, true, a3, e1);
        Libro l3 = ls.crearLibro(3, "Los Juegos del Hambre", 2008, 3, 2, 1, false, a4 , e2);
        Libro l4 = ls.crearLibro(4, "El Arte de la Guerra", 325, 9, 7, 2, true, a2 ,e2);
        Libro l5 = ls.crearLibro(5, "El Principe", 1513, 6, 3, 3, false, a5 ,e3);
        Libro l6 = ls.crearLibro(6, "La Mandrágora", 1518, 4, 3, 1, true, a5, e3);
        Libro l7 = ls.crearLibro(7, "El Silmarillion", 1980, 5, 2, 3, true, a7, e5);
        Libro l8 = ls.crearLibro(8, "El Hobbit", 1965, 6, 5, 4, false, a7, e5);
        Libro l9 = ls.crearLibro(9, "Harry Potter y las Reliquias de la Muerte", 2005, 7, 2, 5, false, a6, e4);
        Libro l10 = ls.crearLibro(10, "Harry Potter y el prisionero de Azkaban", 2003, 3, 2, 1, true, a6, e4);

        
        //----------------------------MENU---------------------------------//

        System.out.println("MENU");
        System.out.println("Ingrese un nùmero");
        System.out.println("1. Para la Búsqueda de un Autor por nombre.");
        System.out.println("2. Para la Búsqueda de un libro por ISBN.");
        System.out.println("3. Para la Búsqueda de un libro por Título.");
        System.out.println("4. Para la Búsqueda de un libro/s por nombre de Autor.");
        System.out.println("5. Para la Búsqueda de un libro/s por nombre de Editorial");
        String opcion = leer.next();
        
        switch (opcion) {
            case "1":
                System.out.println("Ingrese el nombre del Autor");
                String ba = leer.next();
                System.out.println(as.buscarAutorPorNombre(ba));
                break;
            case "2":
                System.out.println("Ingrese el nùmero de ISBN del libro");
                Integer i = leer.nextInt();
                System.out.println(ls.buscarPorISBN(i));
                break;
            case "3":
                System.out.println("Ingrese el tìtulo del libro");
                String t = leer.next();
                System.out.println(ls.buscarPorTitulo(t));
                break;
           case "4":
                System.out.println("Ingrese el nombre del autor para buscar sus libros");
                String blxa = leer.next();
                System.out.println(ls.buscarLibroPorNombreAutor(blxa));
                break;
           case "5":
                System.out.println("Ingrese el nombre de la editorial para buscar sus libros");
                String blxe = leer.next();
                System.out.println(ls.buscarLibroPorEditorial(blxe));
                break;
        }
        

        // Listar todas las tablas
        // ls.listarLibros().forEach((a) -> System.out.println(a.toString()));
        // as.listarAutores().forEach((b) -> System.out.println(b.toString()));
        // es.listareditoriales().forEach((c) -> System.out.println(c.toString()));

    }

    
    
}
