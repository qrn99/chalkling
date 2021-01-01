package teamchalkling.chalkling.login_system;

import org.mindrot.jbcrypt.BCrypt;

public class HashPassword {

    /**
     * Return salt and hash of String input
     * @param input String input
     * @return array of String which contains salt and hash
     */
    public static String[] createSaltAndHash(String input) {
        String salt = BCrypt.gensalt(10);
        String hash = BCrypt.hashpw(input, salt);
        String[] result = {salt, hash};
        return result;
    }

}
