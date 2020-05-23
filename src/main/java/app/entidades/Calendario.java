package app.entidades;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Calendario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 150)
    private String league; // Nombre de la league para obtener la lista de equipos

    private LocalDate fecha; // Sera la fecha de inicio del calendario.

    private LocalTime hora; // Sera la hora de inicio del primer partido en el calendario.

    // Relacion 1:N hacia partido , por medio de esto se carga los datos en la tabla Partido
    @OneToMany(mappedBy = "calendario", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Partido.class)
    private List<Partido> partidos;

    // Se crea dos matrices bidimensionales con numeros, los cuales correspondran a los equipos
    private static Partido[][] ida, vuelta;
    private static int[][] matriz1, matriz2;
    private static int n; // n es el numero de equipos que hay, si el numero de equipos no es par no se crea

    // Contructor
    public Calendario() {
    }

    // Setter y Getter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    // Metodos

    //  Metodo que se encarga de generar los partidos entre equipos
    public void generaCalendario(List<Equipo> equipos, Calendario calendario) {
        partidos = new ArrayList<>();
        n = equipos.size();

        // Se crea dos arrays bidimensionales para configurar los partidos de ida y vuelta
        if (n % 2 == 0) {
            Collections.shuffle(equipos); // Se desordena la lista de equipos
            // this.equipos = equipos;
            matriz1 = new int[n - 1][n / 2];
            matriz2 = new int[n - 1][n / 2];
            /**
             Se rellenan las matrices

             Matriz 1    	 Matriz 2
             1   2   3		6   5   4
             4   5   1		6   3   2
             2   3   4		6   1   5
             5   1   2		6   4   3
             3   4   5		6   2   1
             */
            ida = new Partido[n - 1][n / 2];
            vuelta = new Partido[n - 1][n / 2];
            int cont = 0, cont2 = n - 2;

            // Aqui se inicia la generacion automatica de partidos
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n / 2; j++) {
                    // A la matriz[i][j] se le pasa el valor del cont
                    matriz1[i][j] = cont;
                    cont++; // Se suma cont +1

                    if (cont == (n - 1)) {
                        cont = 0; // Si el cont es igual a n - 1 cont recibe el valor a 0
                    }

                    if (j == 0) { // si j es 0 la segunda matriz en la posicion [i][j] recibe el valor de n - 1
                        matriz2[i][j] = n - 1;
                    } else {
                        matriz2[i][j] = cont2; // matriz2 [i][j] recibe el valor de cont2
                        cont2--; // se le quita uno a cont2

                        if (cont2 == -1) { // si cont2 es igual a -1 cont2 recibirá n - 2
                            cont2 = n - 2;
                        }
                    }

                    // Aqui se organizan los partidos
                    if (j == 0) {
                        // En la matriz de ida se añade los partidos
                        if (i % 2 == 0) { // Si los equipos son pares se agrega los nombres de estos a la tabla Partido y se inicializa el marcador a cero
                            ida[i][j] = new Partido(equipos.get(matriz2[i][j]), equipos.get(matriz1[i][j])); // ==>> VER COMO AGREGAR EL ID DE PARTIDO AL MARCADOR
                            vuelta[i][j] = new Partido(equipos.get(matriz1[i][j]), equipos.get(matriz2[i][j]));
                        } else {
                            ida[i][j] = new Partido(equipos.get(matriz1[i][j]), equipos.get(matriz2[i][j]));
                            vuelta[i][j] = new Partido(equipos.get(matriz2[i][j]), equipos.get(matriz1[i][j]));
                        }
                    } else {
                        ida[i][j] = new Partido(equipos.get(matriz1[i][j]), equipos.get(matriz2[i][j]));
                        vuelta[i][j] = new Partido(equipos.get(matriz2[i][j]), equipos.get(matriz1[i][j]));
                    }
                }
            }
            // Aqui se agrega la jornada a la que pertenece cada par de partidos generados
            int jorn = 1, jorn2 = n;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n / 2; j++) {
                    ida[i][j].setJornada(jorn);
                    vuelta[i][j].setJornada(jorn2);
                }
                jorn++;
                jorn2++;
            }
            addhorario(hora, ida); // Se agrega La hora al Partido
            addhorario(hora, vuelta);
            addfecha(fecha, ida); // Se agrega la fecha al partido
            addfecha(ultimaFecha(fecha), vuelta);

            /** Aqui se asigna el id_calendario a la tabla Partido, de esta forma se podra saber que calendario genero
             * los partidos    */
            addCalendario(ida, calendario);
            addCalendario(vuelta, calendario);

            /**Finalmente aqui se agrega a la lista los partidos generador y se agrega a la tabla partido por medio de
             * la relacion OneToMany entre Calendario y Partido.   */
            addPartidoList(ida);
            addPartidoList(vuelta);
        }

    }

    /**
     * Este metodo servira para asignar hora a los partidos
     *
     * @param hora     Sera la hora para generar el partido
     * @param partidos Sera el array de partidos disponibles para asignarle hora
     */
    private void addhorario(LocalTime hora, Partido[][] partidos) {
        int aux = hora.getHour();
        int hour = hora.getHour();
        int min = hora.getMinute();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n / 2; j++) {
                partidos[i][j].setHora(LocalTime.of(hour, min));
                hour += 2;
            }
            hour = aux;
        }
    }


    /**
     * Este metodo servira para añadir la fecha a cada partido.
     *
     * @param fecha    Sera la fecha para generar el partido.
     * @param partidos Sera el array de partidos disponibles para asignarle hora
     */
    private void addfecha(LocalDate fecha, Partido[][] partidos) {
        LocalDate aux = fecha;
        int dia = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n / 2; j++) {
                partidos[i][j].setFecha(aux);
            }
            dia += 7;
            aux = fecha.plusDays(dia);
        }
    }

    /**
     * Este metodo agregara a la lista de partidos todos que se han generador aleatoriamente para luego almacenarlos
     * en la tabla Partido por medio de la relacion OneToMany entre Calendario y Partido
     *
     * @param duelo Sera el array bidimensional con el par de equipos que protagonizaran el partido(duelo)
     */
    private void addPartidoList(Partido[][] duelo) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n / 2; j++) {
                partidos.add(duelo[i][j]);
            }
        }
    }

    /**
     * Este metodo devolvera la ultima fecha de la primera vuelta para generar el siguiente partido
     *
     * @param fecha Sera la ultima fecha asignada al yltimo partido creado.
     * @return Devuelve una fecha distinta a la ultima asignada.
     */
    private LocalDate ultimaFecha(LocalDate fecha) {
        LocalDate aux = fecha;
        int dia = 0;
        for (int i = 0; i < n - 1; i++) {
            dia += 7;
            aux = fecha.plusDays(dia);
        }
        return aux;
    }


    /**
     * Este metodo agrega el id de calendario a la tabla de partidos, de esta forma se podra saber que numero de calendario
     * generro los partidos
     *
     * @param partido    Sera el array de partidos disponibles para asignarle el id de calendario que los genero
     * @param calendario Sera el objeto Calendarios para poder asignar el id de este
     */
    private void addCalendario(Partido[][] partido, Calendario calendario) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n / 2; j++) {
                partido[i][j].setCalendario(calendario);
            }
        }
    }


}
