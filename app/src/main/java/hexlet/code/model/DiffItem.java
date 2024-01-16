package hexlet.code.model;

import hexlet.code.enums.Operations;

import java.util.Objects;

public final class DiffItem {
    private Operations status;
    private Object prevVal;
    private Object curVal;

    public DiffItem(Object curVal) {
        this.curVal = curVal;
    }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DiffItem diffItem = (DiffItem) o;
        return Objects.equals(curVal, diffItem.curVal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(curVal, prevVal, status);
    }
}
