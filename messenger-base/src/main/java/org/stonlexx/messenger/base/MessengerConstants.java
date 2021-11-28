package org.stonlexx.messenger.base;

import org.stonlexx.messenger.base.scheduler.SchedulerManager;

import java.util.regex.Pattern;

public final class MessengerConstants {

    // Default application data.
    public static final String LAUNCHER_TITLE       = "EbloMessenger";
    public static final String LAUNCHER_VERSION     = "v1.0";

    // Default user settings.
    public static final String DEFAULT_AVATAR_ID    = "0NoAvatarIcon.png";
    public static final String DEFAULT_USER_NAME    = "ноунейм";

    // Patterns.
    public static final Pattern EMAIL_PATTERN       = Pattern.compile("[A-z0-9]+[@][a-z]{2,8}.[a-z]{2,5}");
    public static final Pattern LOGIN_PATTERN       = Pattern.compile("[A-zА-я0-9_]{4,16}");
    public static final Pattern PASSWORD_PATTERN    = Pattern.compile("[A-z0-9]{6,32}");

    // Protocol connection.
    public static final String SERVER_HOST          = "127.0.0.1";
    public static final int SERVER_PORT             = 25016;


    // Default managers.
    public static final SchedulerManager SCHEDULER_MANAGER = new SchedulerManager();
}
