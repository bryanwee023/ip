package duke.util;

public class Utility {

    /**
     * Extracts an integer from an input string, specifically,the integer after the prefix.
     * e.g. Extracts 2 from "done 2"
     *
     * @param input input string
     * @param prefix prefix string
     * @return extracted integer
     * @throws DukeException extracted substring is not a valid integer
     */
    //TODO: refactor to not include prefix
    public static int getIdFromString(String input, String prefix) throws DukeException {
        String idString = "";
        try {
            if (!input.startsWith(prefix) || input.length() <= prefix.length())
                throw new DukeException("you did not specify a duke.task id");

            idString = input.substring(prefix.length());
            return Integer.parseInt(idString);

        } catch (NumberFormatException e) {
            String msg = String.format("\"%s\" is not a valid integer", idString);
            throw new DukeException(msg);
        }
    }
}
