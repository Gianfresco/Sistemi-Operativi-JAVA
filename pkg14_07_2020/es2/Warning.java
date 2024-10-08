package pkg14_07_2020.es2;

import java.io.Serializable;

public class Warning implements Serializable {
    private String msg = null;
    private int val = 0;

    public Warning(String msg, int val) {
        this.msg = msg;
        this.val = val;
    }

    public String getMessage() {
        return msg;
    }

    public int getValore() {
        return val;
    }
}
