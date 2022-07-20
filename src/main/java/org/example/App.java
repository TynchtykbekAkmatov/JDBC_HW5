package org.example;


import org.example.service.UserServiceImpl;

import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {
        // реализуйте алгоритм здесь


        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Tablica koshuu uchun 1 di basynyz.\n" +
                    "Tablicaga maalymat koshuu uchun 2 ni basynyz.\n" +
                    "Tablicany ochuruu uchun 3 tu basynyz.\n" +
                    "Tablicagdan id boyuncha ochuruu uchun 4 tu basynyz\n" +
                    "Bardyk koldonuuchulardy aluu uchun 5 ti basynyz\n" +
                    "Koldonuuchulardy tazaloo ochun 6 ny basynyz\n");
            UserServiceImpl userService1 = new UserServiceImpl();

            int san = scanner.nextInt();
            switch (san) {
                case 1:
                    userService1.createUsersTable();
                    break;
                case 2:
                    jardamchy();
                    break;
                case 3:
                    userService1.dropUsersTable();
                    break;
                case 4:
                    System.out.println("id jazynyz:");
                    Long id = scanner.nextLong();
                    userService1.removeUserById(id);
                    break;
                case 5:
                    System.out.println(userService1.getAllUsers());
                    break;
                case 6:
                    userService1.cleanUsersTable();
                    break;
                default:
                    System.out.println("1 den 6 ga cheinki sandardy jazynyz!");
            }
        }
    }
    public static void jardamchy () {
        UserServiceImpl userService1 = new UserServiceImpl();
        Scanner scanner = new Scanner(System.in);

        System.out.println("koldonuuchunun atyn jana familiasyn jazynyz: ");
        String nameLname = scanner.nextLine();
        String[] nln = nameLname.split(" ");
        System.out.println("koldonuuchunun jashyn jazynyz: ");
        Byte age = scanner.nextByte();

        userService1.saveUser(nln[0], nln[1], age);
    }

//        userService1.saveUser("Timur","Akmatov", (byte) 20);
//        userService1.saveUser("Adilet","Akmatov", (byte) 19);
//        userService1.saveUser("Tynchtykbek","Akmatov", (byte) 30);
//        userService1.saveUser("Uson","Asanov", (byte) 25);
//        userService1.dropUsersTable();
//        userService1.removeUserById(1);


//        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl();
//        userDaoJdbc.createUsersTable();
}
