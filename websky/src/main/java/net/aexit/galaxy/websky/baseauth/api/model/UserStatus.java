package net.aexit.galaxy.websky.baseauth.api.model;

/**
 * Created by hanxt on 17-8-14.
 */
public enum UserStatus {
    OK((byte)1, "启用"), FREEZED((byte)2, "冻结"), DELETED((byte)3, "被删除");

    byte code;
    String message;

    UserStatus(byte code, String message) {
        this.code = code;
        this.message = message;
    }

    public byte getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String valueOf(Integer value) {
        if (value == null) {
            return "";
        } else {
            for (UserStatus ms : UserStatus.values()) {
                if (ms.getCode() == value) {
                    return ms.getMessage();
                }
            }
            return "";
        }
    }
}
