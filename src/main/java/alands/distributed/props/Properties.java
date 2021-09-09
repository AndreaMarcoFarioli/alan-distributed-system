package alands.distributed.props;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Properties {
    String patternString =
            "^\\s*(?<key>\\w+)\\s*=\\s*(?<value>.+)(?<=\\S)\\s*$";
    Pattern pattern = Pattern.compile(patternString);
    String NAME = "key", VALUE = "value";

    Set<String> getKeySet();

    Collection<String> getValues();

    String getString(String key);

    int getInt(String key);

    boolean getBool(String key);

    String setProperty(String key, String value);

    String deleteProperty(String key);

    Properties clone();

    static void addAll(Properties properties, BufferedReader bufferedReader) throws IOException {
        String line = "";
        while ((line = bufferedReader.readLine()) != null){
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches())
                properties.setProperty(matcher.group(NAME), matcher.group(VALUE));
        }
    }

    static Properties join(Properties pTarget, Properties pSource){
        pSource.getKeySet().forEach(s -> {
            pTarget.setProperty(s, pSource.getString(s));
        });
        return pTarget;
    }

    static Properties join(Properties pTarget, Properties... properties){
        for (Properties prop : properties)
            join(pTarget, prop);
        return pTarget;
    }


}
