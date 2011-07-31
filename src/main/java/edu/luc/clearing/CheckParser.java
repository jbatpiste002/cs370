package edu.luc.clearing;

public class CheckParser {
private static enum NUMBER_DEFS {
no (0),
zero (0),
one (1),
two (2),
three (3),
four (4),
five (5),
six (6),
seven (7),
eight (8),
nine (9),
ten (10),
eleven (11),
twelve (12),
thirteen (13),
fourteen (14),
fifteen (15),
sixteen (16),
seventeen (17),
eighteen (18),
nineteen (19),
twenty (20),
thirty (30),
forty (40),
fifty (50),
sixty (60),
seventy (70),
eighty (80),
ninety (90);

private final Integer value;

NUMBER_DEFS(Integer myValue){
value = myValue;
}

public Integer value(){
return value;
}
}

private static enum NUMBER_DEFS_MISSPELLED {
thre (3),
sevn (7),
elevn (11),
twelv (12),
therteen (13),
forteen (14),
fortteen (14),
fourtteen (14),
fiftteen (15),
sxteen (16),
sxtteen (16),
sixtteen (16),
svnteen (17),
sevnteen (17),
sevntteen (17),
svntteen (17),
eihteen (18),
eightteen (18),
ninteen (19),
nintteen (19),
ninetteen (19),
thrty (30),
therty (30),
thirtey (30),
thertey (30),
thrtee (30),
thertee (30),
thirtee (30),
thurtee (30),
fourty (40),
fortey (40),
forety (40),
fourtey (40),
fortee (40),
fourtee (40),
foretee (40),
fiftey (50),
fiftee (50),
fity (50),
fitee (50),
fitey (50),
sxty (60),
sxtey (60),
sxtee (60),
sevnty (70),
sevntey (70),
sevntee (70),
svnty (70),
eitey (80),
eihty (80),
eihtey (80),
eihtee (80),
nintee (90),
nintey (90),
ninty (90);

private final Integer value;
NUMBER_DEFS_MISSPELLED(Integer myValue){
value = myValue;
}

public Integer value(){
return value;
}
}

    public static Double parse(String word) throws NumberFormatException {

if (isTheWordANumber(word)){
return parseNumber(word);
}else if(isTheWordAFraction(word)){
return processFraction(word);
}else{
return parseWord(word);
}

}

    private static Double processFraction(String fraction){
     String[] fractionParts = fraction.split("/",2);
    
     if (fractionParts.length == 1) {
     /*
* must be 4 slashes. String escapes 2, then regex uses one as an escape.
* see Chris Smith @ http://www.velocityreviews.com/forums/t138967-help-using-string-split.html
*/
     fractionParts = fraction.split("\\\\",2);
     }
     //if we still couldn't identify the fraction somehow
     if (fractionParts.length == 1) {
     throw new NumberFormatException("Could not Parse Fraction: " + fraction);
     }
    
     return parse(fractionParts[0]);
    }
    
private static Double parseWord(String word) {


Integer wordValue = checkForWord(word);

if(wordValue == null){
throw new NumberFormatException("Could not Parse Word: " + word);
}

return new Double(wordValue);
}

private static Integer checkForWord(String word){
try{
return Enum.valueOf(NUMBER_DEFS.class, word.toLowerCase()).value();
}catch(Exception e){
//DoNothing
}

try{
return Enum.valueOf(NUMBER_DEFS_MISSPELLED.class, word.toLowerCase()).value();
}catch(Exception ex){
//DoNothing
}

//Find a better way to do this
if(word.equals("3ty")){return 30;}
if(word.equals("4ty")){return 40;}
if(word.equals("5ty")){return 50;}
if(word.equals("6ty")){return 60;}
if(word.equals("7ty")){return 70;}
if(word.equals("8ty")){return 80;}
if(word.equals("9ty")){return 90;}

//if we get here it was unparsable
return null;
}

private static Double parseNumber(String word) {

//Check for Doubles with too many decimal points
String[] splitWordByDecimal = word.split("\\.", 2);
if((splitWordByDecimal.length >= 2) && splitWordByDecimal[1].length() > 3){
throw new NumberFormatException("Could not Parse Number: " + word);
}

return Double.valueOf(word);
}
    
private static boolean isTheWordAFraction(String word) {

if (word.contains("/") || word.contains("\\")) {
return true;
}

return false;
}

//Check if the string is a number in string format
    @SuppressWarnings("unused")
private static boolean isTheWordANumber(String amount){
//TODO find a better way to do this
try{
Double numberValue = Double.valueOf(amount);
}catch(NumberFormatException e){
return false;
}
return true;
    }
    
}


