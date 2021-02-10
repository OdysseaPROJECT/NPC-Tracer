package net.deltamine.ru;

public class Backend {

    public static final boolean SERVER = false;

    public static final boolean DB_HOST = false;

    public static boolean isServer() {
        return SERVER;
    }

    public static boolean isDbHost() {
        return DB_HOST;
    }
}
