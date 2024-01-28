/* (C)2024*/
package org.visual.model.jfa.appkit;

@SuppressWarnings("unused")
public interface NSDockTile extends NSObject {
  String badgeLabel();

  void setBadgeLabel(String value);

  boolean showsApplicationBadge();
}
