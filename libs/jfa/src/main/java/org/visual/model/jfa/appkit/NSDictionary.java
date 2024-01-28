/* (C)2024*/
package org.visual.model.jfa.appkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.visual.model.jfa.annotation.NamedArg;
import org.visual.model.jfa.core.ObjcToJava;
import org.visual.model.jfa.foundation.ID;

@SuppressWarnings("unused")
public interface NSDictionary<K, V> extends NSObject {
  @SuppressWarnings("unchecked")
  static <A, B> NSDictionary<A, B> alloc() {
    return ObjcToJava.alloc(NSDictionary.class);
  }

  @SuppressWarnings("unchecked")
  static <A, B> NSDictionary<A, B> of(Map<A, B> map) {
    List<Map.Entry<A, B>> entries = new ArrayList<>(map.entrySet());
    List<A> keys = entries.stream().map(Map.Entry::getKey).collect(Collectors.toList());
    List<B> values = entries.stream().map(Map.Entry::getValue).collect(Collectors.toList());

    return ObjcToJava.alloc(NSDictionary.class)
        .initWithObjects(NSArray.of(values), NSArray.of(keys));
  }

  int count();

  ID objectForKey(K aKey);

  NSDictionary<K, V> initWithObjects(NSArray<V> objects, @NamedArg("forKeys") NSArray<K> keys);
}
