package com.example.joser.agenda;

/**
 * Created by joser on 16/10/2016.
 */
public class Note {
    //variables de los atributos de la clase note
    public   long id;
    private  String title;
    private  String date;
    private  String site;
    private  String descrip;
    /**
     *constructor vacio
     */
    public Note() {
    }
    /**
     * constructor
     * @param id
     * @param title
     * @param date
     * @param site
     * @param descrip
     */
    public Note(long id,String title, String date, String site, String descrip) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.site = site;
        this.descrip = descrip;
    }
    /**
     * retorna el id
     * @return
     */
    public long getId() {
        return id;
    }
    /**
     * setea el id
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }
    /**
     * retorna el titulo
     * @return
     */
    public String getTitle() {
        return title;
    }
    /**
     *  setea el titulo
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * retorna la fecha
     * @return
     */
    public String getDate() {
        return date;
    }
    /**
     *  setea la fecha
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }
    /**
     * retorna el lugar
     * @return
     */
    public String getSite() {
        return site;
    }
    /**
     *  setea el lugar
     * @param site
     */
    public void setSite(String site) {
        this.site = site;
    }
    /**
     * retorna la descripcion
     * @return
     */
    public String getDescrip() {
        return descrip;
    }
    /**
     *  setea la descripcion
     * @param descrip
     */
    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
    /**
     * retorna el titulo y la hora
     * @return
     */
    @Override
    public String toString() {
        return "Titulo: " + title + " \n " + "Hora: " + date;
    }
}
