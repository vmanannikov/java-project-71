package hexlet.code.storage;

import hexlet.code.enums.Operations;

public class DiffItem {
    private Operations status;
    private Object prevVal;
    private Object curVal;

    public DiffItem(Operations status, Object prevVal, Object curVal) {
        this.status = status;
        this.prevVal = prevVal;
        this.curVal = curVal;
    }

    public Operations getStatus() {
        return status;
    }

    public Object getCurVal() {
        return curVal;
    }

    public Object getPrevVal() {
        return prevVal;
    }
}
