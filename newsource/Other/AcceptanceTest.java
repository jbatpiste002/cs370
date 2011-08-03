package edu.luc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.luc.clearing.CheckParser;

public class AcceptanceTest {

private CheckParser parser;
private String[] array = {"fifty", "five", "55"};

@Before
public void setUp() {
parser = new CheckParser();
}

@Test
public void returnValueOfComplexString() throws Exception {
ArrayList<String> inputs = new ArrayList<String>();
assertThat(convertedString("sixty nine dollars twenty four cents"), is(equalTo(6924)));
assertThat(convertedString("sixty nine dollars and 28 cents"), is(equalTo(6928)));
assertThat(convertedString("59 dollars and one cent"), is(equalTo(5901)));
assertThat(convertedString("59 dollars and 1/100"), is(equalTo(5901)));
assertThat(convertedString("20 dollar and 5 cent"), is(equalTo(2005)));
assertThat(convertedString("Four dollars and no cents" ), is(equalTo(400)));
assertThat(convertedString("fifty five and twelve cents" ), is(equalTo(5512)));
assertThat(convertedString("Three dollars and 0/100 cents"), is(equalTo(300)));
assertThat(convertedString("five and no cents"), is(equalTo(500)));
assertThat(convertedString("one dollars and no cents"), is(equalTo(100)));
assertThat(convertedString("eighty five and 12 cents"), is(equalTo(8512)));
assertThat(convertedString("eighty five dollars 12 cents"), is(equalTo(8512)));
assertThat(convertedString("fifty cent"), is(equalTo(50)));
assertThat(convertedString("1 dollar and one cent"), is(equalTo(101)));
assertThat(convertedString("ten cent"), is(equalTo(10)));
assertThat(convertedString("sixty nine dollars and 69 cents"), is(equalTo(6969)));
assertThat(convertedString("1 dollar and 1 cent"), is(equalTo(101)));
assertThat(convertedString("one dollar and zero cents"), is(equalTo(100)));
assertThat(convertedString("one dollars and one cent"), is(equalTo(101)));
assertThat(convertedString("6 dollars zero cents"), is(equalTo(600)));
assertThat(convertedString("91 dollars and 43 cents"), is(equalTo(9143)));
assertThat(convertedString("one cent"), is(equalTo(1)));
assertThat(convertedString("SEVEN dollars no cents"), is(equalTo(700)));
assertThat(convertedString("6 dollars and 0 cents"), is(equalTo(600)));
assertThat(convertedString("one dollar and 1 cent"), is(equalTo(101)));
assertThat(convertedString("one dollar and one cents"), is(equalTo(101)));
assertThat(convertedString("forty five cents"), is(equalTo(45)));
assertThat(convertedString("one dollars and one cents"), is(equalTo(101)));
assertThat(convertedString("one dollars and one cent"), is(equalTo(101)));
assertThat(convertedString("6 dollars"), is(equalTo(600)));
assertThat(convertedString("twelve dollar and eighty Seven cents"), is(equalTo(1287)));
assertThat(convertedString("twenty dollar and 5 cent"), is(equalTo(2005)));
assertThat(convertedString("sixty eight and TWENTY ONE CENTS"), is(equalTo(6821)));
assertThat(convertedString("one dollar and zero cents"), is(equalTo(100)));
assertThat(convertedString("50 six and 12/100"), is(equalTo(5612)));
assertThat(convertedString("1 dollars and one cents"), is(equalTo(101)));
assertThat(convertedString("Two dollars and twenty one cents"), is(equalTo(221)));
assertThat(convertedString("50 dollars and 11 cents"), is(equalTo(5011)));
assertThat(convertedString("FIVE DOLLARS AND TWELVE CENTS"), is(equalTo(512)));
assertThat(convertedString("fifty nine dollars and 1 cent"), is(equalTo(5901)));
assertThat(convertedString("ten cents"), is(equalTo(10)));
assertThat(convertedString("6 dollars and 65/100"), is(equalTo(665)));
assertThat(convertedString("Ninety-nine and 99/100"), is(equalTo(9999)));
assertThat(convertedString("Fifty-four and 130/100"), is(equalTo(5530)));
assertThat(convertedString("Thirty and 0/100"), is(equalTo(3000)));
assertThat(convertedString("Zero and 100/100"), is(equalTo(100)));
assertThat(convertedString("Zero and zero cents"), is(equalTo(0)));
assertThat(convertedString("Six"), is(equalTo(600)));
assertThat(convertedString("Sixty five"), is(equalTo(6500)));
assertThat(convertedString("Sixty-four"), is(equalTo(6400)));
assertThat(convertedString("64/100"), is(equalTo(64)));
assertThat(convertedString("$60 four + 32 cents"), is(equalTo(6432)));
assertThat(convertedString("twenty-four + 32/100"), is(equalTo(2432)));
assertThat(convertedString("22 + 46/100"), is(equalTo(2246)));
assertThat(convertedString("$45 ~ 46/100"), is(equalTo(4546)));
assertThat(convertedString("80 two , 46/100 cents"), is(equalTo(8246)));
assertThat(convertedString("sixty-eight dollars --- 4/100 cents"), is(equalTo(6804)));
assertThat(convertedString("$forty 5 ~ 16 cents"), is(equalTo(4516)));
assertThat(convertedString("25 -- 47/100"), is(equalTo(2547)));
assertThat(convertedString("80 two , 46/100 cents"), is(equalTo(8246)));
assertThat(convertedString("eighty-9, 46/100 cents"), is(equalTo(8946)));
assertThat(convertedString("eighty eight and twenty-two cents"), is(equalTo(8822)));


}

@Test
public void returnNullForMalformedString() throws Exception {
assertThat(convertedString("purple"), is(nullValue()));
assertThat(convertedString("purple dollars"), is(nullValue()));
assertThat(convertedString("purple dollars and blue cents"), is(nullValue()));
assertThat(convertedString("6 dollars and blue cents"), is(nullValue()));
assertThat(convertedString("purple dollars and 6 cents"), is(nullValue()));
}

@Test
public void createArrayFromStringComponents() throws Exception {
assertThat(createdArray("Nine and 99/100").length, is(equalTo(2)));
assertThat(createdArray("Twenty-two and 10/100")[0], is(equalTo("twenty two")));
assertThat(createdArray("Fifty-one and 9/100")[1], is(equalTo("9")));
assertThat(createdArray("Ninety and 0/100")[1], is(equalTo("0")));
}

@Test
public void convertArrayOfStringsToArrayOfIntegers() throws Exception {
assertThat(convertedArray(array).size(), is(equalTo(3)));
assertThat(convertedArray(array).get(0), is(equalTo(50)));
assertThat(convertedArray(array).get(1), is(equalTo(5)));
assertThat(convertedArray(array).get(2), is(equalTo(55)));
}

private Integer convertedString(String checkString) {
return parser.processCheckString(checkString);
}

private Integer parsedAmountOf(ArrayList<Integer> amounts) {
return parser.parseAmount(amounts).intValue();
}

private String[] createdArray(String input) {
return parser.processString(input);
}

private ArrayList<Integer> convertedArray(String[] input) {
return parser.stringArrToIntegerArr(input);
}

}


