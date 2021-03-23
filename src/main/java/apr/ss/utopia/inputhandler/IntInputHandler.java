package apr.ss.utopia.inputhandler;

public class IntInputHandler extends AbsIntInputHandler {

    public IntInputHandler(Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }

    @Override
    protected Integer getMin() {
        return min;
    }

    @Override
    protected Integer getMax() {
        return max;
    }

}
