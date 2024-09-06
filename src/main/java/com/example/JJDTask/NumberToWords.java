package com.example.JJDTask;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberToWords {

    private static final String[] units = {
            "", "одна", "две", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять", "десять", "одиннадцать",
            "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"
    };

    private static final String[] tens = {
            "", "", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"
    };

    private static final String[] hundreds = {
            "", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"
    };

    public static String convert(BigDecimal amount) {
        int rubles = amount.intValue();
        int kopecks = amount.remainder(BigDecimal.ONE).movePointRight(2).intValue();

        return rublesToWords(rubles) + " рублей " + kopecksToWords(kopecks) + " копеек";
    }

    private static String rublesToWords(int rubles) {
        if (rubles == 0) return "ноль";

        StringBuilder result = new StringBuilder();
        int thousands = rubles / 1000;
        int remainder = rubles % 1000;

        if (thousands > 0) {
            result.append(hundreds[thousands / 100]).append(" ");
            result.append(tens[thousands % 100 / 10]).append(" ");
            result.append(units[thousands % 10]).append(" тысяч ");
        }

        result.append(hundreds[remainder / 100]).append(" ");
        result.append(tens[remainder % 100 / 10]).append(" ");
        result.append(units[remainder % 10]);

        return result.toString().trim();
    }

    private static String kopecksToWords(int kopecks) {
        if (kopecks == 0) return "ноль";

        return units[kopecks % 100] + " копеек";
    }
}