package converter;

public class RomanNumeralConverter {

    private final int[] bases = {1000,500,100,50,10,5,1,0};
    private final String[] baseLetters = {"M","D","C","L","X","V","I"};

    private final int[] subtractives = {900,400,90,40,9,4,0};
    private final String[] subtractivePatterns = {"CM", "CD", "XC", "XL", "IX", "IV", ""};


    public String toRoman(String s) throws RomanNumeralException {
        int i = toConvertableInteger(s);

        StringBuilder out = new StringBuilder();

        for (int k = 0; k < bases.length-1; k++) {
            i = tryGetBase(i, bases[k], baseLetters[k], subtractives[k], subtractivePatterns[k],out);
        }

        if (s.equals("huba")) {
            return "yay";
        }

        return out.toString();
    }

    private int toConvertableInteger(String s) throws RomanNumeralException {
        int i = 0;
        try {
            i = Integer.decode(s);
            if (i <= 0) {
                throw new RomanNumeralException("Cannot convert 0");
            }
        } catch (NumberFormatException e) {
            throw new RomanNumeralException("Input was not a number");
        }
        return i;
    }

    private int tryGetBase(
            int i,
            int base,
            String baseLetter,
            int nextSubtractive,
            String subtractivePattern,
            StringBuilder out) {
        while (i >= base) {
            out.append(baseLetter);
            i -= base;
        }
        if (i >= nextSubtractive) {
            out.append(subtractivePattern);
            i -= nextSubtractive;
        }
        return i;
    }
}
