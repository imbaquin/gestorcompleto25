package model;

import util.LinkedList;

public class User {

    private int nextTaskId = 1;
    private int maxTaskId = -1;
    private String cedula;
    private String username;
    private String password;
    private LinkedList<Task> tasks;

    public User(String cedula, String username, String password) {
        this.cedula = cedula;
        this.username = username;
        this.password = password;
        this.tasks = new LinkedList<>(); // Inicializamos la lista enlazada de tareas
    }

    // Getters and Setters
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Nuevo getter para obtener las tareas
    public LinkedList<Task> getTasks() {
        return tasks;
    }

    // Nuevo setter para establecer las tareas
    public void setTasks(LinkedList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getMaxTaskId() {
        return maxTaskId;
    }

    public void setMaxTaskId(int maxTaskId) {
        this.maxTaskId = maxTaskId;
    }

    public int getNextTaskId() {
        return nextTaskId++;
    }

    @Override
    public String toString() {
        return cedula + "," + username + "," + password;
    }
}
