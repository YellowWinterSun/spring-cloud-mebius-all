package dy.mebius.sample.sc.common.response;

public class ResResult<T> {

    private int status;
    private String desc;
    private T result;

    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_DEFAULT_FAIL = 0;

    private ResResult(int status, String desc, T result) {
        this.status = status;
        this.desc = (null != desc) ? desc : "";
        this.result = result;
    }

    public static ResResult<?> succeed() {
        return new ResResult(STATUS_SUCCESS, "", null);
    }

    public static <E> ResResult<E> succeed(String desc, E result) {
        return new ResResult<E>(STATUS_SUCCESS, desc, result);
    }

    public static <E> ResResult<E> succeed(E result) {
        return new ResResult<E>(STATUS_SUCCESS, "", result);
    }

    public static ResResult<?> fail() {
        return fail(STATUS_DEFAULT_FAIL, "", null);
    }

    public static <E> ResResult<E> fail(int failStatus, String desc, E result) {
        return new ResResult<E>(failStatus, desc, result);
    }

    public static <E> ResResult<E> fail(String desc, E result) {
        return fail(STATUS_DEFAULT_FAIL, desc, result);
    }

    public static <E> ResResult<E> fail(E result) {
        return fail(STATUS_DEFAULT_FAIL, "", result);
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

    public Object getResult() {
        return result;
    }
}
