package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {

    private final StringBuilder reversedCharSequence;

    public ReversedSequence(String sequence) {
        this.reversedCharSequence = new StringBuilder(sequence).reverse();
    }

    @Override
    public int length() {
        return reversedCharSequence.length();
    }

    @Override
    public char charAt(int index) {
        return reversedCharSequence.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return reversedCharSequence.substring(start, end);
    }

    @Override
    public String toString() {
        return reversedCharSequence.toString();
    }
}
// END
