package mvctask;

import mvctask.bean.User;

public final class Util {
    public static final String DELETED_MARKER = " (deleted)";

    public static boolean isUserDeleted(User user) {
        return user.getName().endsWith(DELETED_MARKER);//check if user name end for deleted or not
    }

    public static void markDeleted(User user) {
        if (User.NULL_USER != user && !Util.isUserDeleted(user)) {// if user not null and not marked - set deleted
            user.setName(user.getName() + DELETED_MARKER);
        }
    }
}