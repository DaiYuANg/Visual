/// 应用状态：决定当前显示启动界面还是编辑界面
#[derive(Default, Clone)]
pub enum AppState {
  #[default]
  Welcome, // 启动界面
  Editor, // 进入编辑器
}
