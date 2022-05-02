package com.noriakijr.grpc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnyConnection {

    public static int insert(String name, String email) {
        System.out.println("User inserted:");
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        return 1;
    }

    public static int deleteById(Integer id) {
        System.out.println("Deleted by id: " + id);
        return 1;
    }

    public static List<Map<String, String>> findAll() {
        List<Map<String, String>> users = new ArrayList<>();

        HashMap<String, String> user1 = new HashMap<>();
        user1.put("name", "Luiz");
        user1.put("email", "luiz@gmail.com");

        HashMap<String, String> user2 = new HashMap<>();
        user2.put("name", "marcelo");
        user2.put("email", "marcelo@gmail.com");

        HashMap<String, String> user3 = new HashMap<>();
        user3.put("name", "carlos");
        user3.put("email", "carlos@gmail.com");

        users.add(user1);
        users.add(user2);
        users.add(user3);

        return users;
    }
}
