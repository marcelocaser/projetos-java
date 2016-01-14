package br.com.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ObjectUtil {

    private ObjectUtil() {
    }

    /**
     * Método que irá retornar uma lista com os valores de cada atributo do
     * objeto.
     *
     * @param object É o objeto.
     * @return Lista de valores de cada atributo do objeto.
     */
    public static List<Object> getValores(Object object) {
        ArrayList<Object> valores = new ArrayList<>();
        try {
            Field[] campos = object.getClass().getDeclaredFields();
            for (Field campo : campos) {
                campo.setAccessible(true);

                valores.add(campo.get(object));
            }
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            return null;
        }
        return valores;
    }

    /**
     * Método que informa se o método da classe é do tipo GET.
     *
     * @param method Usando reflection para descrobrir os metodos.
     * @return Verdadeiro se o método for GET, falso caso contrário.
     */
    public static boolean isGetter(Method method) {
        if (!method.getName().startsWith("get")) {
            return false;
        }
        if (method.getParameterTypes().length != 0) {
            return false;
        }
        if (void.class.equals(method.getReturnType())) {
            return false;
        }
        return true;
    }

    /**
     * Método que informa se o método da classe é do tipo SET.
     *
     * @param method Usando reflection para descrobrir os métodos.
     * @return Verdadeiro se o método for SET, falso caso contrário.
     */
    public static boolean isSetter(Method method) {
        if (!method.getName().startsWith("set")) {
            return false;
        }
        if (method.getParameterTypes().length == 0) {
            return false;
        }
        if (!void.class.equals(method.getReturnType())) {
            return false;
        }
        return true;
    }
}
