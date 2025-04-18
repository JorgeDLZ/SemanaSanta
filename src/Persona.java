import java.util.ArrayList;


public class Persona {
    protected String nombre;
    protected String apellido;
    protected int  edad;
    protected String doc;
    protected ArrayList<Perro> perrosAdoptados = new ArrayList<>();

    public Persona() {
    }

    public Persona(String nombre, String apellido, int edad, String doc, ArrayList<Perro> perrosAdoptados) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.doc = doc;
        this.perrosAdoptados = perrosAdoptados;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public ArrayList<Perro> getPerrosAdoptados() {
        return perrosAdoptados;
    }

    public void setPerrosAdoptados(ArrayList<Perro> perrosAdoptados) {
        this.perrosAdoptados = perrosAdoptados;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", doc='" + doc + '\'' +
                ", perrosAdoptados=" + perrosAdoptados +
                '}';
    }

    public void adoptarPerro(Perro perro) {
        int MAX_PERROS=3;
        if (perrosAdoptados.size() < MAX_PERROS) {
            perrosAdoptados.add(perro);
            System.out.println(perro.getNombre() + " ha sido adoptado.");
        } else {
            System.out.println("No se pueden adoptar más perros. Se ha alcanzado el límite de " + MAX_PERROS);
        }
    }
}
