package com.aiSolution.hack.analyzer;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

//import edu.stanford.nlp.pipeline.CoreDocument;
//import edu.stanford.nlp.pipeline.CoreEntityMention;
//import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.Properties;

public class SensitiveDataValidator {

    public static boolean containsSensitiveData(String input) {
        // Create a regular expression pattern that matches email addresses
        Pattern emailPattern = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b");

        // Create a regular expression pattern that matches North American telephone numbers
        Pattern phonePatternNA = Pattern.compile("\\b\\d{3}[ -]?\\d{3}[ -]?\\d{4}\\b");

        // Create a regular expression pattern that matches Indian telephone numbers
        Pattern phonePatternIN = Pattern.compile("\\b\\d{2}[ -]?\\d{5}[ -]?\\d{5}\\b");


        // Create a regular expression pattern that matches passwords
//        String PASSWORD_PATTERN = "(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=])(?=\\S+$).{8,}";
//        String PASSWORD_PATTERN = "\"(?i)\\\\b(?:password|pwd|token)\\\\b.?[\\\\s\\\\p{Punct}]+.?\"";
        String PASSWORD_PATTERN = "^(?=.[a-z])(?=.[A-Z])(?=.\\\\d)(?=.[@])[A-Za-z\\\\d@]{6,12}$\n";

//        String PASSWORD_PATTERN = "(?i)\\b(?:password|pwd|token)\\b.?[\\w!@#$%^&()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]+.*?";

        Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);

        // Create a NER model for identifying person names
//        Properties props = new Properties();
//        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner");
//        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
//
        // Use the NER model to identify person names in the input string
//        String[] tokens = input.split("\\s+");
//        List<String> personNames = new ArrayList<String>();
//        for (int i = 0; i < tokens.length; i++) {
//            CoreDocument doc = new CoreDocument(tokens[i]);
//            pipeline.annotate(doc);
//            for (CoreEntityMention em : doc.entityMentions()) {
//                if (em.entityType().equals("PERSON")) {
//                    System.out.println("Person Name identified!");
//                    personNames.add(em.text());
//                }
//            }
//        }

//         Check if the input string contains person names, email addresses, telephone numbers, passwords, or "pwd", "password", or "token"
//        boolean containsPersonName = !personNames.isEmpty();
        boolean containsEmail = emailPattern.matcher(input).find();
        boolean containsPhoneNA = phonePatternNA.matcher(input).find();
        boolean containsPhoneIN = phonePatternIN.matcher(input).find();
        boolean containsPassword = passwordPattern.matcher(input).find();
        boolean containsPwd = input.toLowerCase().contains("pwd");
        boolean containsPasswordStr = input.toLowerCase().contains("password");
        boolean containsToken = input.toLowerCase().contains("token");
//
        return  //containsPersonName ||
                containsEmail || containsPhoneNA || containsPhoneIN || containsPassword || containsPwd || containsPasswordStr || containsToken;
    }

//    public static void main(String[] args) {
//        System.out.println(containsSensitiveData("john.doe@example.com"));
//
//        // Test North American phone number
//        System.out.println(containsSensitiveData("555-555-5555"));
//        System.out.println(containsSensitiveData("555 555 5555"));
//        System.out.println(containsSensitiveData("5555555555"));
//
//        // Test Indian phone number
//        System.out.println(containsSensitiveData("91 98765 43210"));
//        System.out.println(containsSensitiveData("91 9876543210"));
//        System.out.println(containsSensitiveData("91-9876543210"));
//
////         Test password
//        System.out.println(containsSensitiveData("Password123!"));
//        String str = "Neha@123";
//
//        System.out.println(containsSensitiveData(str));
//
//    }
}