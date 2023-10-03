package com.mycompany.login;




import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme;
import com.mycompany.login.igu.Logininicial;
import javax.swing.UIManager;

public class Login {

    public static void main(String[] args) {
         
       
        try {
           // UIManager.setLookAndFeel( new FlatLightLaf() );
            FlatCyanLightIJTheme.setup();
            UIManager.put( "Button.arc", 999 );
            UIManager.put( "TextComponent.arc", 999 );
            UIManager.put( "Component.arc", 999 );
                    
                    
        } catch( Exception ex ) {
          System.err.println( "Failed to initialize LaF" );
        }
        
        Logininicial principal = new Logininicial();
        
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);
        
        
        
    }
}
