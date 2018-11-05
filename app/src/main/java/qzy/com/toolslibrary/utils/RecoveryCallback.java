package qzy.com.toolslibrary.utils;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2018/11/05
 * desc   :
 * version: 1.0
 */


public interface RecoveryCallback {
    void stackTrace(String stackTrace);

    void cause(String cause);

    void exception(String throwExceptionType, String throwClassName, String throwMethodName, int throwLineNumber);

    void throwable(Throwable throwable);
}
