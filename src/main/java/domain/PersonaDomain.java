package domain;

import java.util.UUID;

public class PersonaDomain {

    private UUID identificador;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String correoElectronico;
    private String contraseña;


    public PersonaDomain(UUID identificador,String primerNombre,String segundoNombre,String primerApellido,String segundoApellido,String correoElectronico,String contraseña){
        setIdentificador(identificador);
        setPrimerNombre(primerNombre);
        setSegundoNombre(segundoNombre);
        setPrimerApellido(primerApellido);
        setSegundoApellido(segundoApellido);
        setCorreoElectronico(correoElectronico);
        setContraseña(contraseña);
    }
    public UUID getIdentificador() {
        return identificador;
    }

    private void setIdentificador(UUID identificador) {
        this.identificador = identificador;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    private void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    private void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    private void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    private void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContraseña() {
        return contraseña;
    }

    private void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    private void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }
}
