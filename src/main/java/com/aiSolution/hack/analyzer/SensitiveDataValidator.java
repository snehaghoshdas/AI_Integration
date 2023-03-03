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
        String PASSWORD_PATTERN = "^(?=.[a-z])(?=.[A-Z])(?=.\\\\d)(?=.[@])[A-Za-z\\\\d@]{6,12}$\n";

        Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);

        boolean containsEmail = emailPattern.matcher(input).find();
        boolean containsPhoneNA = phonePatternNA.matcher(input).find();
        boolean containsPhoneIN = phonePatternIN.matcher(input).find();
        boolean containsPassword = passwordPattern.matcher(input).find();
        boolean containsPwd = input.toLowerCase().contains("pwd");
        boolean containsPasswordStr = input.toLowerCase().contains("password");
        boolean containsToken = input.toLowerCase().contains("token");

        return containsEmail || containsPhoneNA || containsPhoneIN || containsPassword || containsPwd || containsPasswordStr || containsToken;
    }

}