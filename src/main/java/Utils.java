import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public class Utils {
    private static final Pattern NON_ASCII = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

    // Permet d'éviter les problèmes d'accents, de majuscules
    public static String normalize(String input) {
        if (input == null) return null;
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        normalized = NON_ASCII.matcher(normalized).replaceAll("");
        return normalized.toLowerCase(Locale.ROOT);
    }

}
