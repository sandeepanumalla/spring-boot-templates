//package org.secured.springmaildemo.repository;
//
//import org.secured.springmaildemo.model.User;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//@Component
//public class UserRepository {
//
//    List<User> users = new ArrayList<User>();
//
//    public User findByUsername(String username) {
//        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
//    }
//
//
//}
