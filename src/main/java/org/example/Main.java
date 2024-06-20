package org.example;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
    //practice with a regular expression to take a userinput as a string and break apart into numbers and symbols

        Scanner scanner = new Scanner(System.in);
        boolean calculateAgain = true;

        while (calculateAgain) {
            System.out.println("Welcome to THE Calculator!!!");
            System.out.println("PLease enter any numbers followed by: ");
            System.out.println("+ for addition");
            System.out.println("- for subtraction");
            System.out.println("* for multiplication");
            System.out.println("/ for division");
            System.out.println("= for equals");
            System.out.println(" Then press enter");
            System.out.println();
            System.out.println("Example: 234+23*34= ");
            System.out.println();

            String userEntry = scanner.nextLine();

            if (!userEntry.endsWith("=")) {
                System.out.println("Invalid input. Please end with a '=' . ");
            } else {
                userEntry = userEntry.substring(0, userEntry.length() - 1).trim();
                try {
                    System.out.println(userEntry + " " + "=" + calculation(userEntry));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Would you like to calculate again? y) yes or n) no");
            String choice = scanner.nextLine();
            choice.toLowerCase();
            if (choice.toLowerCase().equals("n")) {
                calculateAgain = false;
            }
        }
        System.out.println("Thanks for using THE Calculator!");
    }

        public static double calculation (String userInput){
            List<String> entry = divideNumbersSymbols(userInput);
            return evaluate(entry);
        }

        public static List<String> divideNumbersSymbols (String input){
            List<String> numberSymbol = new ArrayList<>();
            Pattern pattern = Pattern.compile("\\d+|[-+*/%=]");
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                numberSymbol.add(matcher.group());
            }
            return numberSymbol;
        }

        public static double evaluate (List < String > numbersSymbols) {
            if (numbersSymbols.isEmpty()) {
                return 0;
            }
            double result = Integer.parseInt(numbersSymbols.get(0));

            for (int i = 1; i < numbersSymbols.size(); i += 2) {
                String operator = numbersSymbols.get(i);
                int nextOperator = Integer.parseInt(numbersSymbols.get(i + 1));

                switch (operator) {
                    case "+":
                        result += nextOperator;
                        break;
                    case "-":
                        result -= nextOperator;
                        break;
                    case "/":
                        result /= nextOperator;
                        break;
                    case "*":
                        result *= nextOperator;
                        break;
                    case "%":
                        result %= nextOperator;
                        break;
                    default:
                        System.out.println("Invalid operator");
                }
            }
            return result;
        }
    }
