package org.ecommerce.logs;

import java.util.Scanner;

public class Logger {

    private final Scanner SCANNER = new Scanner(System.in);

    public void log(String message) {
        System.out.println(message);
    }
}
