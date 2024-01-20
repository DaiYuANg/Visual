/* (C)2024*/
package org.visual.model.jfa.appkit;

import org.visual.model.jfa.annotation.NamedArg;
import org.visual.model.jfa.core.ObjcToJava;

@SuppressWarnings("unused")
public interface NSMenu extends NSObject {

    static NSMenu alloc() {
        return ObjcToJava.alloc(NSMenu.class);
    }

    String title();

    NSMenu init();

    NSMenu initWithTitle(String title);

    void setTitle(String title);

    void removeAllItems();

    void removeItem(NSMenuItem item);

    void removeItemAtIndex(int index);

    void addItem(NSMenuItem item);

    void insertItem(NSMenuItem item, @NamedArg("atIndex") int index);

    NSMenuItem itemAtIndex(int index);

    long numberOfItems();

    boolean popUpMenuPositioningItem(
            NSMenuItem item, @NamedArg("atLocation") NSPoint location, @NamedArg("inView") NSView view);
}
