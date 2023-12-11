package hexlet.code;

public class DiffItem {
    private String status;
    private Object prevVal;
    private Object curVal;

    public DiffItem(String status, Object prevVal, Object curVal) {
        this.status = status;
        this.prevVal = prevVal;
        this.curVal = curVal;
    }

    public String getStatus() {
        return status;
    }

    public Object getCurVal() {
        return curVal;
    }

    public Object getPrevVal() {
        return prevVal;
    }
}
