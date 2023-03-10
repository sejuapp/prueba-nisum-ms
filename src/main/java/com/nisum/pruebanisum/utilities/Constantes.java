package com.nisum.pruebanisum.utilities;

/**
 * Clase donde se definen las constantes que se van a utilizar en la lógica y peticiones del
 * aplicativo web.
 */
public final class Constantes {

    /**
     * Constantes de la aplicación
     */
    private Constantes() {
        throw new IllegalStateException("Clase de constantes");
    }


    public final class MsgError {
        private MsgError() {
        }

        public static final String NOT_EMPTY = "Campo obligatorio";
        public static final String EMAIL_FORMATO = "Correo no cumple con el formato correcto";
        public static final String PASSWORD_FORMATO = "password no cumple con el formato correcto";
        public static final String EMAIL_REGISTRADO = "El correo ya registrado";
        public static final String EMAIL_OBLIGATORIO = "El correo es obligatorio";
        public static final String NO_EXISTE_PARAMETRO = "No existe una parametrica";
        public static final String NO_EXISTE_PARAMETRO_VALOR = "No existe la expresión para validar";

        public static final String EXPRESION_VACIA = "La expresión no debe estar vacia";
        public static final String EXPRESION_INVALIDA = "La expresión no es válida";

        public static final String USUARIO_NO_EXISTE = "El usuario no existe";
    }


}
