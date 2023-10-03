
package com.mycompany.login.logica;

import com.mycompany.login.persistencia.ControladoraPersistencia;
import java.util.List;


public class Controladora {
    
    ControladoraPersistencia controlPersis;
    Encoder mEncoder;
    
    public Controladora() {
        controlPersis = new ControladoraPersistencia();
        mEncoder  = new Encoder();
        
    }
        
    
    // Creamos el metodo validar usuario
    public Usuario validarUsuario(String usuario, String login) {
        
       // String mensaje ="";
       Usuario user = null;
       String Password = "";
       
        // Recorremos todos los usuario y validamos
        
        List <Usuario> listaUsuarios = controlPersis.traerUsuario();
            
            for(Usuario usu:listaUsuarios){
                
                if (usu.getNombreUsuario().equals(usuario)){
                    
                    if (usu.getContrasenia().equals(login)){
         //               mensaje = "Usuario y Contraseña Correctos, Bienvenido/a!!!";
                          user=usu;
                          
                          return user;
                          
                    }else{
         //               mensaje = "Contraseña incorrecta!!!";
                           user=null;
                          return user;
                    }
                    
                }else{
         //               mensaje = "Usuario incorrecto";
                          user=null;
                          
                        
                }
            }
            return user;
    }

    public List<Usuario> traerUsuarios() {
        return controlPersis.traerUsuario();
        
    }

    public List<Rol> traerRoles() {
        return controlPersis.traerRoles();
    }
    
    
    public void crearUsuario(String usuario, String contra, String rolRecibido) {
       
        /*Creamos un objeto usu de tipo Usuario y traemos los parametros que
        traemos de la interfaz grafica*/
        
        Usuario usu = new  Usuario();
        usu.setNombreUsuario((usuario).trim());
        usu.setContrasenia(mEncoder.ecnode(contra).trim());
        
        /* Esto se hace para el rol debido a que es un objeto pero estamos recibiendo un string, se tiene 
            entonces que buscar en la base de datos y traerlo, para esto creamos un metodo traerRol() aca en la
        controladora "this." y cuando lo encuentre se lo asigne a usu con el metodo .setUnRol, verificando antes que
        el rol se diferente a null*/
        
        Rol rolEncontrado = new Rol();
        rolEncontrado = this.traerRol(rolRecibido);
        
        if (rolEncontrado!=null) {
            
            usu.setUnRol(rolEncontrado);
        }
        
        /* Este metodo nos permite traer el ultimo id del usuario para crearlo manualmente,
         esto cuando ya hay datos creados la base de datos*/
            int id = this.buscarUltimoIdUsuarios();
            
        //Asigna el ultimo id mas 1. 
            usu.setId(id+1);
        
        //Guardamos en la base de datos, mediante el metodo crearUsuario().
        controlPersis.crearUsuario(usu);
        
        
        
    }
    
    // Es buena practica hacer metodos separados, para poderlos reutilizar y que sea lo mas especifico.
    
    private Rol traerRol(String rolRecibido) {
        
        //Hacemos una lista de tipo Rol, para que nos traiga los roles.
        List<Rol> listaRoles = controlPersis.traerRoles();
    
        // El for se lee, cada Rol de la lista  roles
        for(Rol rol: listaRoles){
            /*Si el nombre del rol que traemos es igual al que nos dio la persona,
            retornenos ese rol y si no es, retorne null, es decir nada*/
            if (rol.getNombreRol().equals(rolRecibido)) {
                return rol;
            }
        }
        return null;
    }

    private int buscarUltimoIdUsuarios() {
        //Creamos una lista de los usuarios
        List<Usuario> listaUsuarios = this.traerUsuarios();
        
        //Permite traer el ultimo usuario en la base de datos
        Usuario usu = listaUsuarios.get(listaUsuarios.size()-1);
        
        return usu.getId();
    }

    public void borrarUsuario(int id_usuario) {
        controlPersis.borrarUsuario(id_usuario);
    }

    

    public Usuario traerUsuario(int id_usuario) {
        return controlPersis.traerUsuario(id_usuario);
    }

    public void editarUsuario(Usuario usu, String usuario, String contra, String rolRecibido) {
        usu.setNombreUsuario(usuario); 
        usu.setContrasenia(mEncoder.ecnode(contra).trim());
        
        
        Rol rolEncontrado = new Rol();
        rolEncontrado = this.traerRol(rolRecibido);
        
        if (rolEncontrado!=null) {
            
            usu.setUnRol(rolEncontrado);
        }
        
        controlPersis.editarUsuario(usu);
        
        
    }

    
    
}
