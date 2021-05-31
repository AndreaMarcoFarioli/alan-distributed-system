package alands.distributed;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnvLoader {
    private final BufferedReader bufferedReader;
    private static final String patternString =
            "^ *(?<name>[a-z]+) *= *((?<int>[0-9]+)|(?<bool>true|false)|(?:(?<string>[a-z]*))) *$";
    private final Pattern pattern = Pattern.compile(patternString);
    private static final String NAME = "name", INT = "int", BOOL = "bool", STRING = "string";
    private Map<String, Object> envVars = new HashMap<>();
    public EnvLoader(InputStream inputStream){
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public void load() throws IOException {
        String line = "";
        while ((line = bufferedReader.readLine()) != null){
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()){
                String name = matcher.group(NAME);
                if (matcher.group(INT) != null)
                    envVars.put(name, Integer.parseInt(matcher.group(INT)));
                else if (matcher.group(STRING) != null)
                    envVars.put(name, matcher.group(STRING));
                else if (matcher.group(BOOL) != null)
                    envVars.put(name, Boolean.getBoolean(matcher.group(BOOL)));
            }
        }
    }

    public String getString(String name){
        return (String) envVars.get(name);
    }

    public Integer getInt(String name){
        return (Integer) envVars.get(name);
    }

    public Boolean getBool(String name){
        return (Boolean) envVars.get(name);
    }


    @Override
    public String toString() {
        return "EnvLoader{" +
                "bufferedReader=" + bufferedReader +
                ", pattern=" + pattern +
                ", envVars=" + envVars +
                '}';
    }
}
