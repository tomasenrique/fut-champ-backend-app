package app.enumeradores;

public enum Posicion {
    POR("Portero"),
    LIB("Libero"),
    LI("Lateral izquierdo"),
    LD("Lateral derecho"),
    DFC("Defensa central"),
    DFI("Defensa izquierda"),
    DFD("Defensa derecha"),
    MCD("Mediocentro defensivo"),
    MC("Centrocampista medio centro"),
    MD("Centrocampista medio derecho"),
    MI("Centrocampista medio izquierdo"),
    MCO("Centrocampista medio centro ofensivo"),
    ED("Exterior derecho"),
    EI("Exterior izquierdo"),
    DC("Delantero centro"),
    SD("Segundo delantero"),
    SDI("Segundo delantero izquierdo"),
    SDD("Segundo delantero derecho ");

    private String posicion;

    // Constructor
    Posicion(String posicion) {
        this.posicion = posicion;
    }

    //Getter

    /**
     * Para obtener el valor de la posicion abreviada al usar la nomenclatura del punto.
     * EJM
     * Persona persona = new Persona();
     * persona.setPosicion(Posicion.MC.getPosicion());
     *
     * @return Devuelve una cadena de texto que sera el valor de la abreviatura.
     */
    public String getPosicion() {
        return posicion;
    }

    // Metodo

    /**
     * Obtiene el valor de la posicion abreviada por medio de una cadena de texto abreviada, ejm: "POR" => Portero
     * EJM
     * Persona persona = new Persona();
     * persona.setPosicion(Posicion.getPosicion("POR"));
     *
     * @param posicion Sera la cadena de texto de la abreviada de la posicion del juggador
     * @return Devuelve una cadena de texto que sera el valor de la abreviatura.
     */
    public static String getPosicion(String posicion) {
        Posicion abreviatura = Enum.valueOf(Posicion.class, posicion.toUpperCase());
        return abreviatura.getPosicion();
    }
}

