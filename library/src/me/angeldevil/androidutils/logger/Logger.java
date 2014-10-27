
package me.angeldevil.androidutils.logger;

import android.text.TextUtils;
import android.util.Log;

public final class Logger {

    private static final String LOG_FORMAT = "%1$s\n%2$s";

    public static final int VERBOSE = Log.VERBOSE;
    public static final int DEBUG = Log.DEBUG;
    public static final int INFO = Log.INFO;
    public static final int WARN = Log.WARN;
    public static final int ERROR = Log.ERROR;
    public static final int DISABLE = Log.ERROR + 1;

    private static volatile int LEVEL = INFO;

    private Logger() {
    }

    /**
     * Set the log level.
     * 
     * @param level Log level, logs that level is less than this value will not
     *            be logged.
     * @see {@link #VERBOSE}, {@link #DEBUG}, {@link #INFO}, {@link #WARN},
     *      {@link #ERROR}
     */
    public static void setLogLevel(int level) {
        LEVEL = level;
    }
    
    public static void disableLog() {
        LEVEL = DISABLE;
    }

    public static void d(String tag, String message, Object... args) {
        log(tag, Log.DEBUG, null, message, args);
    }

    public static void d(String tag, Throwable e, String message, Object... args) {
        log(tag, Log.DEBUG, e, message, args);
    }

    public static void i(String tag, String message, Object... args) {
        log(tag, Log.INFO, null, message, args);
    }

    public static void i(String tag, Throwable e, String message, Object... args) {
        log(tag, Log.INFO, e, message, args);
    }

    public static void w(String tag, Throwable e) {
        log(tag, Log.WARN, e, null);
    }

    public static void w(String tag, String message, Object... args) {
        log(tag, Log.WARN, null, message, args);
    }

    public static void w(String tag, Throwable e, String message, Object... args) {
        log(tag, Log.WARN, e, message, args);
    }

    public static void e(String tag, Throwable ex) {
        log(tag, Log.ERROR, ex, null);
    }

    public static void e(String tag, String message, Object... args) {
        log(tag, Log.ERROR, null, message, args);
    }

    public static void e(String tag, Throwable ex, String message, Object... args) {
        log(tag, Log.ERROR, ex, message, args);
    }

    private static void log(String tag, int priority, Throwable ex, String message, Object... args) {
        if (priority < LEVEL)
            return;
        if (args.length > 0) {
            message = String.format(message, args);
        }

        String log;
        if (ex == null) {
            log = message;
        } else {
            String logMessage = message == null ? ex.getMessage() : message;
            String logBody = Log.getStackTraceString(ex);
            log = String.format(LOG_FORMAT, logMessage, logBody);
        }
        if (TextUtils.isEmpty(log)) {
            return;
        }
        Log.println(priority, tag, log);
    }
}
