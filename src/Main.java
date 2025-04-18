import java.util.*;
public class  Main {
    public static void main(String[] args) {
        boolean estado = true;
        int opcion;

        List<Perro> lstperros = new ArrayList<>();
        List<Persona> lstpersonas = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("""
                    1.	Registrar personas (se guardan en una lista).
                    2.	Registrar perros (se agregan a la lista de disponibles).
                    3.	Ver personas registradas.
                    4.	Ver perros disponibles.
                    5.	Adoptar perro (una persona puede adoptar hasta 3).
                    6.	Consultar el perro más viejo adoptado por una persona.
                    7.	Salir del programa
                    """);
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> {
                    System.out.println("Ingrese su nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingrese su apellido: ");
                    String apellido = scanner.nextLine();
                    System.out.println("Ingrese su edad: ");
                    int edad = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Ingrese su documento: ");
                    String doc = scanner.nextLine();

                    //Crear una nueva instancia de Persona
                    Persona nuevaPersona = new Persona(nombre, apellido, edad, doc, new ArrayList<>());

                    // Agregar la instancia a la lista
                    lstpersonas.add(nuevaPersona);
                    System.out.println("Persona registrada exitosamente.");

                }
                case 2 -> {
                    System.out.println("Ingrese la placa del perro");
                    String placa = scanner.nextLine();
                    System.out.println("Ingrese el nombre del perro");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingrese la raza del perro");
                    String raza = scanner.nextLine();
                    System.out.println("Ingrese la edad del perro");
                    int edad = scanner.nextInt();
                    System.out.println("Ingrese el tamaño del perro(cm)");
                    String tamanio = scanner.nextLine();

                    Perro nuevoPerro = new Perro(placa, nombre, raza, edad, tamanio);

                    lstperros.add(nuevoPerro);
                    System.out.println("Perro registrado con exitosamente");

                }
                case 3 -> {
                    System.out.println("\n--- Personas registradas ---");
                    if (lstpersonas.isEmpty()) {
                        System.out.println("No hay personas registradas");
                    } else {
                        for (Persona persona : lstpersonas) {
                            System.out.println(persona);
                        }
                    }
                    System.out.println("---------------------------\n");
                }
                case 4 -> {
                    System.out.println("\n--- Perros disponibles ---");
                    if (lstperros.isEmpty()) {
                        System.out.println("No hay perros disponibles");
                    } else {
                        for (Perro perroDisponible : lstperros) {
                            System.out.println(perroDisponible);
                        }
                    }
                    System.out.println("---------------------------\n");
                }
                case 5 ->{
                    System.out.println("\n--- Adoptar Perro ---");

                    System.out.print("Ingrese el documento de la persona que desea adoptar: ");
                    String documentoPersona = scanner.nextLine();

                    // Buscar a la persona en la lista de personas registradas
                    Persona personaEncontrada = null;
                    for (Persona persona : lstpersonas) { // Iteramos sobre cada persona en la lista de personas registradas
                        if (persona.getDoc().equals(documentoPersona)) { // Comparamos
                            personaEncontrada = persona;
                            break;
                        }
                    }
                    if (personaEncontrada == null) {
                        System.out.println("No se encontró ninguna persona con ese documento.");
                    } else {

                        if (personaEncontrada.getPerrosAdoptados().size() >= 3) {
                            System.out.println(personaEncontrada.getNombre() + " ya ha alcanzado el límite de 3 adopciones.");
                        } else {
                            //Verificar si hay perros disponibles para adoptar
                            if (lstperros.isEmpty()) {
                                System.out.println("No hay perros disponibles para adoptar en este momento.");
                            } else {
                                //Perros disponibles
                                System.out.println("\n--- Perros Disponibles ---");
                                for (Perro perroDisponible : lstperros) {
                                    System.out.println(perroDisponible);
                                }

                                System.out.print("Ingrese la placa del perro que desea adoptar: ");
                                String placaPerro = scanner.nextLine();

                                Perro perroEncontrado = null;
                                for (Perro perroDisponible : lstperros) {
                                    if (perroDisponible.getPlaca().equals(placaPerro)) { // Comparamos la placa
                                        perroEncontrado = perroDisponible;
                                        break;
                                    }
                                }

                                if (perroEncontrado == null) {
                                    System.out.println("No se encontró ningún perro disponible con esa placa.");
                                } else {
                                    //Realizar la adopción
                                    personaEncontrada.adoptarPerro(perroEncontrado); // Llamada al método
                                    lstperros.remove(perroEncontrado);
                                    System.out.println("Adopción realizada con éxito");
                                }
                            }
                        }
                    }
                    System.out.println("-----------------------\n");
                }
                case 6 ->{
                    System.out.println("\n--- Consultar el perro más viejo adoptado ---");

                    System.out.print("Ingrese el documento de la persona que desea hace la consulta: ");
                    String documentoPersona = scanner.nextLine();
                    Persona personaConsulta = null;
                    for (Persona persona : lstpersonas) {
                        if (persona.getDoc().equals(documentoPersona)) {
                            personaConsulta = persona;
                            break;
                        }
                    }

                    if (personaConsulta == null) {
                        System.out.println("No se encontró ninguna persona con ese documento.");
                    } else {
                        List<Perro> perrosAdoptadosPersona = personaConsulta.getPerrosAdoptados();
                        if (perrosAdoptadosPersona.isEmpty()) {
                            System.out.println(personaConsulta.getNombre() + " aún no ha adoptado ningún perro.");
                        } else {

                            Perro perroMasViejo = perrosAdoptadosPersona.get(0); // Asumimos que el primero es el más viejo inicialmente
                            for (int i = 1; i < perrosAdoptadosPersona.size(); i++) {
                                Perro perroActual = perrosAdoptadosPersona.get(i);
                                if (perroActual.getEdad() > perroMasViejo.getEdad()) {
                                    perroMasViejo = perroActual;
                                }
                            }
                            System.out.println("El perro más viejo adoptado por " + personaConsulta.getNombre() + " es:");
                            System.out.println(perroMasViejo);
                        }
                    }
                    System.out.println("-------------------------------------------\n");

                }
                case 7->{
                    System.out.println("Saliendo del programa...");
                    estado=false;
                }
                default -> {
                    System.out.println("Opcion no valida...");

                }

            }
        } while (estado) ;

    }
}