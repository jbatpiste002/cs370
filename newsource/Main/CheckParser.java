package edu.luc.clearing;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class CheckParser {

Map<String, Integer> AMOUNTS;

public CheckParser() {
AMOUNTS = new HashMap<String, Integer>();
AMOUNTS.put("no", 0);
AMOUNTS.put("cent", 1);
AMOUNTS.put("dollar", 100);
AMOUNTS.put("zero", 0);
AMOUNTS.put("one", 1);
AMOUNTS.put("two", 2);
AMOUNTS.put("three", 3);
AMOUNTS.put("four", 4);
AMOUNTS.put("five", 5);
AMOUNTS.put("six", 6);
AMOUNTS.put("seven", 7);
AMOUNTS.put("eight", 8);
AMOUNTS.put("nine", 9);
AMOUNTS.put("ten", 10);
AMOUNTS.put("eleven", 11);
AMOUNTS.put("twelve", 12);
AMOUNTS.put("thirteen", 13);
AMOUNTS.put("fourteen", 14);
AMOUNTS.put("fifteen", 15);
AMOUNTS.put("sixteen", 16);
AMOUNTS.put("seventeen", 17);
AMOUNTS.put("eighteen", 18);
AMOUNTS.put("nineteen", 19);
AMOUNTS.put("twenty", 20);
AMOUNTS.put("thirty", 30);
AMOUNTS.put("forty", 40);
AMOUNTS.put("fifty", 50);
AMOUNTS.put("sixty", 60);
AMOUNTS.put("seventy", 70);
AMOUNTS.put("eighty", 80);
AMOUNTS.put("ninety", 90);
AMOUNTS.put("hundred", 100);
AMOUNTS.put("thousand", 1000);
}

public Integer processCheckString(String input) {

ArrayList<Integer> dollars = null;
ArrayList<Integer> cents = null;
String[] result = processString(input);
String dollarString = result[0];
String centString = result[1];
Integer dollarTotal = null;
Integer centTotal = null;
Integer total = null;

if (dollarString == null && centString == null)
return total;
else if (dollarString != null && centString == null) {
dollars = stringArrToIntegerArr(dollarString.split(" "));
total = parseAmount(dollars);
if (total != null)
total *= 100;
return total;
}
else if (dollarString == null && centString != null) {
cents = stringArrToIntegerArr(centString.split(" "));
total = parseAmount(cents);
return total;
}
else {
dollars = stringArrToIntegerArr(dollarString.split(" "));
dollarTotal = parseAmount(dollars);
cents = stringArrToIntegerArr(centString.split(" "));
centTotal = parseAmount(cents);
if (dollarTotal != null) {
dollarTotal *= 100;
if (centTotal != null)
total = dollarTotal + centTotal;
}
return total;
}
}

public boolean isNumeric(String word) {
if (word != null)
return java.util.regex.Pattern.matches("\\d+", word);
else return false;
}

public ArrayList<Integer> stringArrToIntegerArr(String[] stringArr) {
ArrayList<Integer> integerList = new ArrayList<Integer>();
for (String word : stringArr) {
if (isNumeric(word))
integerList.add(Integer.valueOf(word));
else
integerList.add(AMOUNTS.get(word));
}
return integerList;
}

public Integer parseAmount(ArrayList<Integer> numArr) {
if (numArr.contains(null)) return null;
Integer total = numArr.get(0);
for (int i = 1; i < numArr.size(); i++) {
if (numArr.get(i) < numArr.get(i - 1))
total += numArr.get(i);
else total *= numArr.get(i);
}
return total;
}

public String[] processString(String input) {
String[] inputArr;
String dollarSide = null;
String centSide = null;
input = input.toLowerCase();

//replace "+ / & / ~ / -- / ---" with "and"
if (input.contains(" + "))
input = input.replace(" + ", " and ");
else if (input.contains(" & "))
input = input.replace(" & ", " and ");
else if (input.contains(" ~ "))
input = input.replace(" ~ ", " and ");
else if (input.contains(" -- "))
input = input.replace(" -- ", " and ");
else if (input.contains(" --- "))
input = input.replace(" --- ", " and ");
else if (input.contains(" , "))
input = input.replace(" , ", " and ");
else if (input.contains(", "))
input = input.replace(", ", " and ");

//remove hyphens
input = input.replace('-', ' ');

//remove "$" sign
if (input.contains("$"))
input = input.replace("$", "");

if (!(input.contains("dollars") || input.contains("dollar") || input.contains("and")
|| input.contains("cent") || input.contains("cents") || input.contains("/100"))) {
dollarSide = input;
String[] result = {dollarSide, centSide};
return result;
}

//create normal form of [dollars] and [cents]
if (input.contains("dollars") && input.contains("and"))
input = input.replace(" dollars ", " ");
else if (input.contains("dollar") && input.contains("and"))
input = input.replace(" dollar ", " ");
else if (input.contains("dollars") && !input.contains("and"))
input = input.replace("dollars", "and");
else if (input.contains("dollar") && !input.contains("and"))
input = input.replace("dollar", "and");

//split into dollar and cent components
if (input.contains(" and ")) {
inputArr = input.split(" and ");
dollarSide = inputArr[0];
centSide = inputArr[1];
}
else if (input.contains(" and")) {
inputArr = input.split(" and");
dollarSide = inputArr[0];
centSide = "0";
}
else if (!input.contains("and"))
centSide = input;

//remove the word cents from cent side
if (centSide.contains(" cents"))
centSide = centSide.replace(" cents", "");
if (centSide.contains(" cent"))
centSide = centSide.replace(" cent", "");
if (centSide.contains("/100"))
centSide = centSide.replace("/100", "");

String[] result = {dollarSide, centSide};
return result;
}
}
