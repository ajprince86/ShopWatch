package com.shopwatch.utils;

import java.util.HashMap;
import java.util.Map;

public class TestDataManager {

        // A Map to store all our test users
        // Key = userType, Value = User object
        private static Map<String, User> users = new HashMap<>();

        // Static block - runs once when the class is loaded
        static {
            users.put("standard",
                    new User("standard_user", "secret_sauce", "standard"));
            users.put("locked",
                    new User("locked_out_user", "secret_sauce", "locked"));
            users.put("problem",
                    new User("problem_user", "secret_sauce", "problem"));
            users.put("performance",
                    new User("performance_glitch_user", "secret_sauce", "performance"));
        }

        // Method to get a user by type
        public static User getUser(String userType) {
            return users.get(userType);
        }
    }


