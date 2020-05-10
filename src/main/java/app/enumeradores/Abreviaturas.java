package app.enumeradores;

public enum Abreviaturas {
    JUG("Jugador"),
    COO("Coordinador");

    private String abreviaturas;

    // Constructor
    Abreviaturas(String abreviaturas) {
        this.abreviaturas = abreviaturas;
    }

    //Getter

    /**
     * Para obtener el valor de la abreviatura al usar la nomenclatura del punto.
     * EJM
     * Persona persona = new Persona();
     * persona.getOcupacion(Abreviaturas.JUG.getAbreviaturas());
     *
     * @return Devuelve una cadena de texto que sera el valor de la abreviatura.
     */
    public String getAbreviaturas() {
        return abreviaturas;
    }

    // Metodo

    /**
     * Obtiene el valor de la abreviatura por medio de una cadena de texto abreviada, ejm: "JUG" => Jugador
     * EJM
     * Persona persona = new Persona();
     * persona.getOcupacion(Abreviaturas.getAbreviaturas("JUG"));
     *
     * @param abreviaturas Sera la cadena de texto de la abreviada de la posicion del juggador
     * @return Devuelve una cadena de texto que sera el valor de la abreviatura.
     */
    public static String getAbreviaturas(String abreviaturas) {
        Posicion abreviatura = Enum.valueOf(Posicion.class, abreviaturas.toUpperCase());
        return abreviatura.getPosicion();
    }
}
