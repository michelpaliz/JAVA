package Testing.Objects.Book;

public class Book {

    private String nombre;
    private double precio;

    public Book(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }


    public String getNombre() {
        return nombre;
    }


    public double getPrecio() {
        return precio;
    }


    @Override
    public String toString() {
        return "Book [nombre=" + nombre + ", precio=" + precio + "]";
    }

}
