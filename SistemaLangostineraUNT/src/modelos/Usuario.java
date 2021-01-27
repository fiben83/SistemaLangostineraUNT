/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

public class Usuario implements Entidad{
    
    private Integer id;
    private String nick;
    private String password;
    private Integer id_menu;
    private String email;
    private String telefono;
    private Integer id_persona;
    private String estado;
    
    private Menu menu;
    
    public Usuario(String nick ,String email,String telefono , Integer id_menu,Integer id_persona)
    {
        this.nick = nick;
        this.email = email;
        this.telefono = telefono;
        this.id_menu = id_menu;
        this.id_persona = id_persona;
    }
    
    public Usuario(String nick ,String email,String telefono, String password,Integer id_menu,String estado)
    {
        this.nick = nick;
        this.email = email;
        this.telefono = telefono;
        this.password=password;
        this.id_menu = id_menu;
        this.estado = estado;
    }
    public Usuario(String nick,String password,Integer idPersona){
        this.nick = nick;
        this.password = password;
        this.id_persona = idPersona;
    }
    public Usuario(String nick , String password )
    {
        this.nick = nick;
        this.password = password;
    }

    public Integer getId_menu() {
        return id_menu;
    }

    public void setId_menu(Integer id_menu) {
        this.id_menu = id_menu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Integer getId_persona() {
        return id_persona;
    }

    public void setId_persona(Integer id_persona) {
        this.id_persona = id_persona;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
}
