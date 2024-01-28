/* (C)2024*/
package org.visual.model.jfa.core;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.PointerByReference;
import org.visual.model.jfa.annotation.Superclass;
import org.visual.model.jfa.appkit.NSInvocation;
import org.visual.model.jfa.appkit.NSMethodSignature;
import org.visual.model.jfa.appkit.NSObject;
import org.visual.model.jfa.foundation.Foundation;
import org.visual.model.jfa.foundation.ID;

@Superclass("NSProxy")
public class FoundationProxy {
  private final NSObject target;
  private final FoundationProxyHandler handler;
  private final Pointer respondsToSelector;

  public FoundationProxy(NSObject target, FoundationProxyHandler handler) {
    this.target = target;
    this.handler = handler;

    respondsToSelector = Foundation.createSelector("respondsToSelector:");
  }

  @SuppressWarnings("unused")
  public void forwardInvocation(NSInvocation invocation) {
    if (Boolean.FALSE.equals(handler.hasAdditionalMethods())) return;
    Pointer selector = new Pointer(invocation.selector().longValue());
    if (respondsToSelector.equals(selector)) {
      PointerByReference ref = new PointerByReference();
      invocation.getArgument(ref, 2);
      if (handler.hasAdditionalMethod(ref.getValue())) {
        ByteByReference result = new ByteByReference((byte) 1);
        invocation.setReturnValue(result);
        return;
      }
    } else {
      FoundationMethod additionalMethod = handler.getAdditionalMethod(selector);
      if (additionalMethod != null) {
        additionalMethod.invoke(invocation);
        return;
      }
    }

    if (handler.beforeTarget(invocation)) {
      invocation.invokeWithTarget(target);
      handler.afterTarget(invocation);
    }
  }

  @SuppressWarnings("unused")
  public NSMethodSignature methodSignatureForSelector(ID sel) {
    if (handler.hasAdditionalMethods()) {
      FoundationMethod additionalMethod = handler.getAdditionalMethod(new Pointer(sel.longValue()));
      if (additionalMethod != null) {
        return additionalMethod.getMethodSignature();
      }
    }
    return target.methodSignatureForSelector(sel);
  }
}
