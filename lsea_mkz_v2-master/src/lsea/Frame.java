package lsea;

/*** enum type for type of Frame:<br>carbon<br>steel<br>aluminum<br>magnesium<br>titanium<br>Method -> public static boolean contains(String s)*/
public enum Frame{
    carbon,steel,aluminum,magnesium,titanium;
    /**
     *  Checking if given value is in enum type
     * @param s -> String value
     * @return true if given string is one of enum<br> false if it is not
     */
    public static boolean contains(String s)
    {
        for(Frame frame:values())
            if (frame.name().equals(s))
                return true;
        return false;
    }
}