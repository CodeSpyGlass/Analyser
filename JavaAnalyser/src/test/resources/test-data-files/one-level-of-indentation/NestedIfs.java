public class NestedIfs {
    public int tooManyIfs() {
        int i = 0;
        if (true) {
            if (true) {
                i = 1;
            }
        }
        return i;
    }
}