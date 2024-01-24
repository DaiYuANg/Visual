package org.visual.model.debugger.core;

import lombok.Getter;

@Getter
public enum DebuggerContextJ {

//    INSTANCE;
//
//    private final BeanScope beanScope = BeanScope.builder()
//            .shutdownHook(true)
//            .build();
//
//    public <T> @NotNull T get(Class<T> clazz) {
//        return beanScope.get(clazz);
//    }
//
//    private VirtualMachine virtualMachine;
//
//    @SneakyThrows
//    public Parent load(String prefix) {
//        val loader = new FXMLLoader(VisualModelDebugger.class.getResource(prefix + ".fxml"));
//        loader.setControllerFactory(beanScope::get);
//        loader.setCharset(StandardCharsets.UTF_8);
//        return loader.load();
//    }
//
//    public boolean isAttached() {
//        return Optional.ofNullable(virtualMachine).isPresent();
//    }
//
//    public boolean isNotAttached(){
//        return Optional.ofNullable(virtualMachine).isEmpty();
//    }
}
