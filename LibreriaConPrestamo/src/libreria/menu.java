package libreria;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Cliente;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.servicios.AutorServicio;
import libreria.servicios.ClienteServicio;
import libreria.servicios.EditorialServicio;
import libreria.servicios.LibroServicio;
import libreria.servicios.PrestamoServicio;

public class menu {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    private final LibroServicio ls = new LibroServicio();
    private final AutorServicio as = new AutorServicio();
    private final EditorialServicio es = new EditorialServicio();
    private final PrestamoServicio ps = new PrestamoServicio();
    private final ClienteServicio cs = new ClienteServicio();

    public menu() {
        ls.setServicios(es, as, ps, cs);
        as.setServicios(es, ls, ps, cs);
        es.setServicios(as, ls, ps, cs);
        ps.setServicios(es, as, ls, cs);
        cs.setServicios(es, as, ls, ps);
    }

    public void eliminarPrestamo() throws Exception {
        System.out.println("Ingrese id del préstamo para eliminarlo");
        int id = leer.nextInt();
        ps.eliminarPrestamo(id);

    }

    public void ejecucion() throws ParseException, Exception {

        //-----------------------AUTORES-------------------------------//
        Autor a1 = as.crearAutor(1, "Dan Brown", true);
        Autor a2 = as.crearAutor(2, "Sun Tzu", false);
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
        Libro l3 = ls.crearLibro(3, "Los Juegos del Hambre", 2008, 3, 2, 1, false, a4, e2);
        Libro l4 = ls.crearLibro(4, "El Arte de la Guerra", 325, 9, 7, 2, true, a2, e2);
        Libro l5 = ls.crearLibro(5, "El Principe", 1513, 6, 3, 3, false, a5, e3);
        Libro l6 = ls.crearLibro(6, "La Mandrágora", 1518, 4, 3, 1, true, a5, e3);
        Libro l7 = ls.crearLibro(7, "El Silmarillion", 1980, 5, 2, 3, true, a7, e5);
        Libro l8 = ls.crearLibro(8, "El Hobbit", 1965, 6, 5, 4, false, a7, e5);
        Libro l9 = ls.crearLibro(9, "Harry Potter y las Reliquias de la Muerte", 2005, 7, 2, 5, false, a6, e4);
        Libro l10 = ls.crearLibro(10, "Harry Potter y el prisionero de Azkaban", 2003, 3, 2, 1, true, a6, e4);

        //----------------------------MENU---------------------------------//
        ps.listarPrestamos().forEach((d) -> System.out.println(d.toString()));

        System.out.println("MENU");
        System.out.println("Ingrese un nùmero");
        System.out.println("1. Para crear un nuevo cliente");
        System.out.println("2. Para registar el préstamo de un libro / prestar un libro");
        System.out.println("3. Para eliminar un cliente");
        System.out.println("4. Para eliminar un prestamo / devolver un libro");
        System.out.println("5. Búsqueda de todos los préstamos de un cliente");
        System.out.println("6. Editar un cliente");
        System.out.println("7. Editar un préstamo");

        Date d = new Date();
        String opcion = leer.next();

        switch (opcion) {
            case "1":
                System.out.println("Ingrese id, dni, nombre, apellido, telefono");
                cs.crearCliente(leer.nextInt(), leer.nextLong(), leer.next(), leer.next(), leer.next());

                System.out.println("---- Listado de Clientes -----");
                cs.listarClientes().forEach((lc) -> System.out.println(lc.toString()));
                break;

            case "2":

                System.out.println("Ingrese el ISBN del libro a prestar: el 0 es el 1");
                Libro idl = ls.listarLibros().get(leer.nextInt());

                System.out.println("Ingrese el id del cliente... El cliente 1 es el 0");
                Cliente idc = cs.listarClientes().get(leer.nextInt());

                System.out.println("Ingrese la fecha en que se efectuó el préstamo en formato dd/MM/yyyy");
                String fp = leer.next();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String datep = fp;
                // Se crea un objeto date con la fecha ingresada por usuario.
                Date fechaprest = sdf.parse(datep);

                if (d.after(fechaprest)) {
                    System.out.println("No se puede prestar/crear libros con fecha anterior a la fecha actual");
                    break;
                }

                System.out.println("Ingrese la fecha en que se devolvió el libro en formato dd/MM/yyyy");
                String fd = leer.next();
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                String dated = fd;
                Date fechadev = df.parse(dated);

                if (d.after(fechadev)) {
                    System.out.println("No se puede devolver/crear libros con fecha anterior a la fecha actual");
                    break;
                }

                if (fechaprest.after(fechadev)) {
                    System.out.println("No se puede devolver/crear libros con fecha anterior a la fecha del Préstamo");
                    break;
                }

                System.out.println("Ingrese id del préstamo");

                int i = leer.nextInt();

                if (i < 0) {
                    System.out.println("No se puede introducir un id inferior a o");
                    break;
                }

                ps.crearPrestamo(i, fechaprest, fechadev, idl, idc);
                ls.logicaPrestamo(idl, ps.crearPrestamo(i, fechaprest, fechadev, idl, idc), i);
                ps.listarPrestamos().forEach((lp) -> System.out.println(lp.toString()));
                ls.listarLibros().forEach((a) -> System.out.println(a.toString()));
                break;

            case "3":
                System.out.println("Ingrese el id del cliente a eliminar");
                int cae = leer.nextInt();
                cs.eliminarCliente(cae);
                cs.listarClientes().forEach((lc) -> System.out.println(lc.toString()));
                break;
            case "4":
                System.out.println("Ingrese el id del préstamo a eliminar");
                int pae = leer.nextInt();
                ps.buscarPrestamoporId(pae);
                System.out.println("Ingrese el ISBN del libro viculado a ese préstamo: el 0 es el 1");
                Libro id2 = ls.listarLibros().get(leer.nextInt());
                ps.eliminarPrestamo(pae);
                ls.logicaDevoluvion(id2, ps.buscarPrestamoporId(pae), pae);
                ps.listarPrestamos().forEach((lp) -> System.out.println(lp.toString()));
                ls.listarLibros().forEach((a) -> System.out.println(a.toString()));
                break;
            case "5":
                System.out.println("Ingrese el id del cliente para ver todos sus préstamos");
                int idcliente = leer.nextInt();
                ps.buscarPrestamosporCliente(idcliente).forEach((mc) -> System.out.println(mc.toString()));
                break;

            case "6":
                System.out.println("Ingrese el id del cliente a editar");
                int idce = leer.nextInt();
                System.out.println("Ingrese dni, nombre, apellido, telefono del cliente a editar");
                cs.editarCliente(idce, leer.nextLong(), leer.next(), leer.next(), leer.next());
                cs.listarClientes().forEach((lcl) -> System.out.println(lcl.toString()));

                break;
            case "7":
                System.out.println("Ingrese el id del préstamo a editar");
                int ced = leer.nextInt();

                System.out.println("Ingrese la fecha en que se efectuó el préstamo en formato dd/MM/yyyy");
                String fpr = leer.next();
                SimpleDateFormat sdfo = new SimpleDateFormat("dd/MM/yyyy");
                String datepr = fpr;
                // Se crea un objeto date con la fecha ingresada por usuario.
                Date fechapresta = sdfo.parse(datepr);

                if (d.after(fechapresta)) {
                    System.out.println("No se puede prestar libros con fecha anterior a la fecha actual,");
                    break;
                }

                System.out.println("Ingrese la fecha en que se devolvió el libro en formato dd/MM/yyyy");
                String fde = leer.next();
                SimpleDateFormat dfor = new SimpleDateFormat("dd/MM/yyyy");
                String datede = fde;
                Date fechadevol = dfor.parse(datede);

                if (d.after(fechadevol)) {
                    System.out.println("No se puede devolver libros con fecha anterior a la fecha actual");
                    break;
                }

                if (fechapresta.after(fechadevol)) {
                    System.out.println("No se puede devolver libros con fecha anterior a la fecha del Préstamo");
                    break;
                }

                System.out.println("Ingrese el id del libro a prestar.. el libro 1 es el 0");
                Libro idli = ls.listarLibros().get(leer.nextInt());

                System.out.println("Ingrese el id del cliente... El cliente 1 es el 0");
                Cliente idcl = cs.listarClientes().get(leer.nextInt());

                ps.editarPrestamo(ced, fechapresta, fechadevol, idli, idcl);
                ps.listarPrestamos().forEach((lp) -> System.out.println(lp.toString()));
                break;
        }

        // Listar todas las tablas
        // ls.listarLibros().forEach((a) -> System.out.println(a.toString()));
        // as.listarAutores().forEach((b) -> System.out.println(b.toString()));
        // es.listareditoriales().forEach((c) -> System.out.println(c.toString()));
    }

}
