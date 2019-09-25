package Debugger;

public class Debugger {
    private static boolean isEnabled = false;

    public static void enableDebugger() { isEnabled = true; }

    public static void disableDebugger() { isEnabled = false; }

    public static boolean isEnabled() { return isEnabled; }

    public static void log (Object obj) {
        if (isEnabled)
            return;

        System.out.println( obj.toString() );
    }
}
