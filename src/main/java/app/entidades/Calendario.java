package app.entidades;

import javax.persistence.*;
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

    // Relacion 1:N hacia partido
    @OneToMany(mappedBy = "calendario", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Partido.class)
    private List<Partido> partidos;


    // relacion 1:N hacia equipo
    @OneToMany(mappedBy = "calendario", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Equipo.class)
    private List<Equipo> equipos;


    // Relacion 1:1 hacia liga
    @OneToOne(mappedBy = "calendario", cascade = CascadeType.ALL)
    private League league;


    // se crea dos matrices bidimensionales con numeros, los cuales correspondran a los equipos
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

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }


    // Metodos

    public void generaCalendario(List<Equipo> equipos, LocalDate fecha, LocalTime hora) {
        partidos = new ArrayList<>();
        n = equipos.size();
        // se crea dos arrays bidimensiionales para configurar los partidos de ida y vuelta

        if (n % 2 == 0) {
            //se desordena la lista de equipos
            Collections.shuffle(equipos);
            // this.equipos = equipos;
            matriz1 = new int[n - 1][n / 2];
            matriz2 = new int[n - 1][n / 2];
		/*
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

            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n / 2; j++) {
                    // a la matriz[i][j] se le pasa el valor del cont
                    matriz1[i][j] = cont;
                    // se suma cont +1
                    cont++;
                    // si el cont es igual a n - 1 cont recibe el valor a 0
                    if (cont == (n - 1)) cont = 0;
                    // si j es 0 la segunda matriz en la posicion [i][j] recibe el valor de n - 1
                    if (j == 0) matriz2[i][j] = n - 1;
                    else {
                        // matriz2 [i][j] recibe el valor de cont2
                        matriz2[i][j] = cont2;
                        // se le quita uno a cont2
                        cont2--;
                        // si cont2 es igual a -1 cont2 recibirá n - 2
                        if (cont2 == -1) cont2 = n - 2;
                    }
                    // aqui se organizan los partidos
                    if (j == 0) {
                        // en la matriz de ida se añade los partidos
                        if (i % 2 == 0) {
                            ida[i][j] = new Partido(equipos.get(matriz2[i][j]), equipos.get(matriz1[i][j]), new Marcador());
                            vuelta[i][j] = new Partido(equipos.get(matriz1[i][j]), equipos.get(matriz2[i][j]), new Marcador());
                        } else {
                            ida[i][j] = new Partido(equipos.get(matriz1[i][j]), equipos.get(matriz2[i][j]), new Marcador());
                            vuelta[i][j] = new Partido(equipos.get(matriz2[i][j]), equipos.get(matriz1[i][j]), new Marcador());
                        }
                    } else {
                        ida[i][j] = new Partido(equipos.get(matriz1[i][j]), equipos.get(matriz2[i][j]), new Marcador());
                        vuelta[i][j] = new Partido(equipos.get(matriz2[i][j]), equipos.get(matriz1[i][j]), new Marcador());
                    }
                }
            }
            int jorn = 1, jorn2 = n;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n / 2; j++) {
                    ida[i][j].setJornada(jorn);
                    vuelta[i][j].setJornada(jorn2);
                }
                jorn++;
                jorn2++;
            }
            addhorario(hora, ida);
            addhorario(hora, vuelta);
            addfecha(fecha, ida);
            addfecha(ultimaFecha(fecha), vuelta);
            addPartidoList(ida);
            addPartidoList(vuelta);
        }

    }

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

    // añadimos la fecha
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

    private void addPartidoList(Partido[][] duelo) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n / 2; j++) {
                partidos.add(duelo[i][j]);
            }

        }
    }

    // obtenemos la ultima fecha de la primera vuelta
    private LocalDate ultimaFecha(LocalDate fecha) {
        LocalDate aux = fecha;
        int dia = 0;
        for (int i = 0; i < n - 1; i++) {
            dia += 7;
            aux = fecha.plusDays(dia);
        }
        return aux;
    }


}
