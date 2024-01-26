/* (C)2024*/
package org.visual.model.jfa.core;

import static org.visual.model.jfa.foundation.Foundation.getObjcClass;

import com.sun.jna.NativeMapped;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.ByReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.val;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.visual.model.jfa.annotation.Protocol;
import org.visual.model.jfa.appkit.NSObject;
import org.visual.model.jfa.foundation.CGFloat;
import org.visual.model.jfa.foundation.Foundation;
import org.visual.model.jfa.foundation.ID;
import org.visual.model.jfa.foundation.VarArgs;

public class ObjcToJava implements InvocationHandler {
    private static final String EQUALS = "equals";
    private static final String HASH_CODE = "hashCode";
    private static final String TO_STRING = "toString";
    private static final String DESCRIPTION = "description";

    private final ID id;

    private ObjcToJava(ID id) {
        this.id = id;
    }

    public static <T extends NSObject> T alloc(Class<T> clazz) {
        return invokeStatic(clazz, "alloc");
    }

    public static <T extends NSObject> T invokeStatic(@NotNull Class<T> clazz, String selector, Object... args) {
        if (clazz.isAnnotationPresent(Protocol.class)) {
            throw new IllegalArgumentException("Cannot allocate protocols.");
        }

        ID instance = Foundation.invoke(getObjcClass(clazz.getSimpleName()), selector, args);
        return map(instance, clazz);
    }

    @SuppressWarnings("unchecked")
    public static <T> @Nullable T map(ID result, Class<T> javaType) {
        if (NSObject.class.isAssignableFrom(javaType)) {
            return mapNSObject(result, javaType);
        }
        if (String.class.isAssignableFrom(javaType)) {
            return (T) Foundation.toStringViaUTF8(result);
        }
        if (long.class == javaType || Long.class == javaType) {
            return (T) Long.valueOf(result.longValue());
        }
        if (int.class == javaType || Integer.class == javaType) {
            return (T) Integer.valueOf(result.intValue());
        }
        if (double.class == javaType || Double.class == javaType) {
            return (T) Double.valueOf(result.doubleValue());
        }
        if (float.class == javaType || Float.class == javaType) {
            return (T) Float.valueOf(result.floatValue());
        }
        if (boolean.class == javaType || Boolean.class == javaType) {
            return (T) Boolean.valueOf(result.booleanValue());
        }
        if (byte.class == javaType || Byte.class == javaType) {
            return (T) Byte.valueOf(result.byteValue());
        }
        if (short.class == javaType || Short.class == javaType) {
            return (T) Short.valueOf(result.shortValue());
        }
        if (ID.class.isAssignableFrom(javaType) || Object.class == javaType) {
            return (T) result;
        }
        if (Void.class == javaType || void.class == javaType) {
            return null;
        }
        if (Pointer.class == javaType) {
            return (T) new Pointer(result.longValue());
        }

        throw new IllegalArgumentException(javaType.getSimpleName() + " is not supported.");
    }

    @Contract("_, _ -> !null")
    private static <T> T mapNSObject(ID id, @NotNull Class<T> clazz) {
        return clazz.cast(
                Proxy.newProxyInstance(ObjcToJava.class.getClassLoader(), new Class[]{clazz}, new ObjcToJava(id)));
    }

    public static Optional<Class<?>> getJavaClass(ID id) {
        return getJavaClass(id, NSObject.class.getPackage());
    }

    public static Optional<Class<?>> getJavaClass(ID id, Package containingPackage) {
        if (id == null || ID.NIL.equals(id) || containingPackage == null) {
            return Optional.empty();
        }
        try {
            Pointer classForCoderSelector = Foundation.createSelector("classForCoder");
            if (respondsToSelector(id, classForCoderSelector)) {
                ID classNameId = Foundation.invoke(id, classForCoderSelector);
                String className = Foundation.stringFromClass(classNameId);
                return Optional.of(Class.forName(containingPackage.getName() + "." + className));
            }
        } catch (ClassNotFoundException | RuntimeException ignored) {

        }
        return Optional.empty();
    }

    private static boolean respondsToSelector(ID id, Pointer classNameSelector) {
        return Foundation.invoke(id, "respondsToSelector:", classNameSelector).booleanValue();
    }

    public static Object toFoundationArgument(Object arg) {
        if (arg instanceof Structure || arg instanceof CGFloat) {
            return arg;
        }

        return toID(arg);
    }

    public static ID toID(Object arg) {
        if (arg == null) {
            return ID.NIL;
        } else if (isFoundationProxy(arg)) {
            return getIdFromProxy(arg);
        } else if (arg instanceof String) {
            return Foundation.nsString((String) arg);
        } else if (arg instanceof ID) {
            return (ID) arg;
        } else if (arg instanceof Number) {
            return new ID(((Number) arg).longValue());
        } else if (arg instanceof Pointer) {
            return new ID((Pointer) arg);
        } else if (arg instanceof ByReference) {
            return new ID(((ByReference) arg).getPointer());
        } else if (arg instanceof Method) {
            return new ID(Selector.forMethod((Method) arg));
        } else if (arg instanceof NativeMapped) {
            return toID(((NativeMapped) arg).toNative());
        } else if (arg instanceof Enum<?>) {
            return Foundation.nsString(((Enum<?>) arg).name());
        } else if (arg instanceof Boolean) {
            return new ID((boolean) arg ? 1L : 0L);
        }

        throw new IllegalArgumentException(arg.getClass().getSimpleName() + " is not supported");
    }

    private static ID getIdFromProxy(Object arg) {
        return ((ObjcToJava) Proxy.getInvocationHandler(arg)).getId();
    }

    private static boolean isFoundationProxy(@NotNull Object arg) {
        return Proxy.isProxyClass(arg.getClass()) && Proxy.getInvocationHandler(arg) instanceof ObjcToJava;
    }

    private ID getId() {
        return id;
    }

    @Override
    public Object invoke(Object proxy, @NotNull Method method, Object[] args) {
        switch (method.getName()) {
            case EQUALS -> {
                return isFoundationProxy(args[0]) && Objects.equals(getId(), getIdFromProxy(args[0]));
            }
            case HASH_CODE -> {
                return id.hashCode();
            }
            case TO_STRING -> {
                return Foundation.toStringViaUTF8(Foundation.invoke(id, DESCRIPTION));
            }
            default -> {
                return invokeNative(method, args);
            }
        }
    }

    private @Nullable Object invokeNative(Method method, Object[] args) {
        val foundationArguments = getFoundationArguments(args);
        val selector = Selector.stringForMethod(method);

        ID result = Foundation.invoke(id, selector, foundationArguments);
        return !isPrimitiveType(method.getReturnType()) && Foundation.isNil(result)
                || void.class == method.getReturnType() ? null : wrapReturnValue(method, result);

    }

    private boolean isPrimitiveType(Class<?> returnType) {
        return boolean.class == returnType
                || int.class == returnType
                || long.class == returnType
                || double.class == returnType
                || float.class == returnType;
    }

    private Object wrapReturnValue(Method method, ID result) {
        val returnType = getReturnType(method, result);
        return map(result, returnType);
    }

    private Class<?> getReturnType(@NotNull Method method, ID result) {
        Class<?> returnType = method.getReturnType();

        if (!NSObject.class.isAssignableFrom(returnType)) {
            return returnType;
        }
        Optional<Class<?>> javaClass = getJavaClass(result);
        if (javaClass.isPresent()) {
            return javaClass.get();
        }

        return returnType;
    }

    @Contract("null -> new")
    private Object @NotNull [] getFoundationArguments(Object[] args) {
        return args == null
                ? new Object[0]
                : Arrays.stream(args)
                .flatMap(this::flattenVarArgs)
                .map(ObjcToJava::toFoundationArgument)
                .toArray();
    }

    private Stream<Object> flattenVarArgs(Object value) {
        if (value instanceof VarArgs) {
            return Stream.concat(((VarArgs<?>) value).getArgs().stream(), Stream.of((Object) null));
        }
        return Stream.of(value);
    }
}
