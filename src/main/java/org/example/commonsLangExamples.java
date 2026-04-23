package org.example;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class commonsLangExamples {
    public static void main(String[] args) {

    String text2 = "hello world";
    String numberText = "12345";
    String wrongNumber = "12ab";

    System.out.println("---- StringUtils----");
    System.out.println("The following text will be capitalized: " + StringUtils.capitalize(text2));
    System.out.println("The following text will be reversed: " + StringUtils.reverse(text2));
    System.out.println("The following text will be in Upper Case: " + StringUtils.upperCase(text2));
    System.out.println("The following text will be contains the word: hello: " + StringUtils.contains(text2, "hello"));

    System.out.println("\n---- NumberUtils ----");
    System.out.println(numberText + "This string can be converted to number: " + NumberUtils.isCreatable(numberText));
    System.out.println(wrongNumber + "This string cannot be converted to number: " + NumberUtils.isCreatable(wrongNumber));
    System.out.println("The maximum number between '5, 10, 3' is: " + NumberUtils.max(5, 10, 3));

    System.out.println("\n----ObjectUtils ----"); //!!! returns null if the object is empy otherwise returns the object
    String name = null;
    System.out.println(ObjectUtils.defaultIfNull(name, "Unknown value"));

    System.out.println("\n---- RandomStringUtils----"); //!! to generate random strings based on criteria
    System.out.println("Random alphabetic: " + RandomStringUtils.secure().nextAlphabetic(8)); //
    System.out.println("Random numeric: " + RandomStringUtils.secure().nextNumeric(6));
}
}
