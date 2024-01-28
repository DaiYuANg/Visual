package org.visual.component.loading;

public class LoadingItem {
    public  int weight;
    public  String name;
//    public final BooleanSupplierEx<? extends Throwable> loadFunc;

    public LoadingItem(int weight, String name, Runnable loadFunc) {
//        this(weight, name, () -> {
//            loadFunc.run();
//            return true;
//        });
    }
//
//    public LoadingItem(int weight, String name, BooleanSupplierEx<? extends Throwable> loadFunc) {
////        this.weight = weight;
////        this.name = name;
////        this.loadFunc = loadFunc;
//    }
}
