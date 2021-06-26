package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Ivan", "Ivanov", (byte) 30);
        userService.saveUser("Oleg", "Olegovoch", (byte) 27);
        userService.saveUser("Som", "Somov", (byte) 18);
        userService.saveUser("Tom", "Tomsky", (byte) 5);

        userService.getAllUsers();
        userService.dropUsersTable();
    }
}