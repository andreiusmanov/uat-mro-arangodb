package uz.uat.mro.apps.utils;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.server.VaadinSession;

public class MyUtils {
    public static Object getAttribute(String name) {
        return VaadinSession.getCurrent().getAttribute(name);
    }

    public static void setAttribute(String key, Object value) {
        VaadinSession.getCurrent().setAttribute(key, value);
    }

    public static void showSuccessNotification(String message) {
        Notification.show(message, 3000, Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    }

    public static void showCancelNotification(String string) {
        Notification.show(string, 3000, Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_ERROR);
    }
}
