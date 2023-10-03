
package com.mycompany.login.persistencia;

import com.mycompany.login.logica.Rol;
import com.mycompany.login.logica.Usuario;
import com.mycompany.login.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/*La controladora de persistencia es la encargada de la comuniciacion con la BD.
 se crean los metodos para modificar la BD*/

public class ControladoraPersistencia {
    
    UsuarioJpaController usuarioJpa = new UsuarioJpaController();
    RolJpaController rolJpa = new RolJpaController();

    //Metodos de consulta en la BD, similar al select * from usuarios.
    
    public List<Usuario> traerUsuario() { 
        return usuarioJpa.findUsuarioEntities();
    }

    public List<Rol> traerRoles() {
        return rolJpa.findRolEntities();
    }
    
    
    // Metodo para crear usuario en la BD
    public void crearUsuario(Usuario usu) {
        usuarioJpa.create(usu);
    }
    
    //Metodo para eliminar usuario
    public void borrarUsuario(int id_usuario) {
        try
        {
            usuarioJpa.destroy(id_usuario);
        } catch (NonexistentEntityException ex)
        {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
    
    // Metodo para traer usuario por Id
    public Usuario traerUsuario(int id_usuario) {
        return usuarioJpa.findUsuario(id_usuario);
    }

    public void editarUsuario(Usuario usu) {
        try 
        {
            usuarioJpa.edit(usu);
        } catch (Exception ex)
        {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
