package com.zero.file.service.vo;

import com.sun.org.apache.regexp.internal.RE;

/**
 * 请求响应结构对象
 * @Author:xuyp
 * @Date:2018/6/10 21:38
 */
public class ResultMessage {

    // 定义常用常量
    public static final String SUCCESS = "success";// 成功
    public static final String ERROR = "error";// 错误
    public static final String FAIL = "fail";// 失败

    /**
     * 请求返回码
     */
    private String code;

    /**
     * 请求返回消息
     */
    private String msg;

    /**
     * 请求返回数据
     */
    private Object data;

    public ResultMessage() {
        this.code = ResultMessage.SUCCESS;
    }

    public ResultMessage(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultMessage{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * 成功的时候返回，避免在业务中过多出现的创建ResultMessage对象的代码
     * @return
     */
    public static ResultMessage success() {
        return new ResultMessage(SUCCESS, SUCCESS);
    }

    // 返回fail消息对象
    public static ResultMessage fail(String msg) {
        return new ResultMessage(FAIL, msg);
    }

    // 返回Error消息对象
    public static ResultMessage error(String msg) {
        return  new ResultMessage(ERROR, msg);
    }
}
