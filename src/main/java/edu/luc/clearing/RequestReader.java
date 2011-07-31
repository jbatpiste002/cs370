package edu.luc.clearing;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RequestReader {
private static Gson gson = new Gson();

public static List<String> RequestToMapParser(Reader jsonData) {
     List<String> checks = gson.fromJson(jsonData, requestType());
     return checks;
}

public static String mapToJson(Map<String, Integer> map) {
return gson.toJson(map);

}

public static String stringListToJson(List<String> list){
return gson.toJson(list, requestType());
}

public static String checkListToJson(List<Check> list){
return gson.toJson(checkListToMap(list));
}

private static Map<String, Long> checkListToMap(List<Check> checkList){
Map<String, Long> mapOfChecks = new LinkedHashMap<String, Long>();

for(Check theCheck : checkList){
mapOfChecks.put(theCheck.getStringValue(), theCheck.getNumberValue());
}

return mapOfChecks;
}
}

