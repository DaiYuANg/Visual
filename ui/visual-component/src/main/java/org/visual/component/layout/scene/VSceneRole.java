package org.visual.component.layout.scene;

public enum VSceneRole {
  MAIN(VSceneRoleConst.BIT_MANAGE_WIDTH | VSceneRoleConst.BIT_MANAGE_HEIGHT),
  TEMPORARY(
      VSceneRoleConst.BIT_MANAGE_WIDTH
          | VSceneRoleConst.BIT_MANAGE_HEIGHT
          | VSceneRoleConst.BIT_TEMPORARY),
  DRAWER_VERTICAL(
      VSceneRoleConst.BIT_MANAGE_HEIGHT
          | VSceneRoleConst.BIT_SHOW_COVER
          | VSceneRoleConst.BIT_TEMPORARY),
  DRAWER_HORIZONTAL(
      VSceneRoleConst.BIT_MANAGE_WIDTH
          | VSceneRoleConst.BIT_SHOW_COVER
          | VSceneRoleConst.BIT_TEMPORARY),
  POPUP(
      VSceneRoleConst.BIT_SHOW_COVER
          | VSceneRoleConst.BIT_TEMPORARY
          | VSceneRoleConst.BIT_CENTRAL_WIDTH
          | VSceneRoleConst.BIT_CENTRAL_HEIGHT),
  ;
  public final boolean manageWidth;
  public final boolean manageHeight;
  public final boolean showCover;
  public final boolean temporary;
  public final boolean centralWidth;
  public final boolean centralHeight;

  VSceneRole(int bits) {
    this.manageWidth =
        (bits & VSceneRoleConst.BIT_MANAGE_WIDTH) == VSceneRoleConst.BIT_MANAGE_WIDTH;
    this.manageHeight =
        (bits & VSceneRoleConst.BIT_MANAGE_HEIGHT) == VSceneRoleConst.BIT_MANAGE_HEIGHT;
    this.showCover = (bits & VSceneRoleConst.BIT_SHOW_COVER) == VSceneRoleConst.BIT_SHOW_COVER;
    this.temporary = (bits & VSceneRoleConst.BIT_TEMPORARY) == VSceneRoleConst.BIT_TEMPORARY;
    this.centralWidth =
        (bits & VSceneRoleConst.BIT_CENTRAL_WIDTH) == VSceneRoleConst.BIT_CENTRAL_WIDTH;
    this.centralHeight =
        (bits & VSceneRoleConst.BIT_CENTRAL_HEIGHT) == VSceneRoleConst.BIT_CENTRAL_HEIGHT;
  }
}
