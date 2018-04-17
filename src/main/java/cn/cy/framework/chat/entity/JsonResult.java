package cn.cy.framework.chat.entity;

/**
 * Created by Administrator on 2018\4\15 0015.
 */
public class JsonResult {

    private String status;

    private Object result;

    public String getStatus() {
        return status;
    }

    public Object getResult() {
        return result;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
