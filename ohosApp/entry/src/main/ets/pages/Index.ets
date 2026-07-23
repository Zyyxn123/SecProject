import { BuilderNameConstants, buildRouterModel, RouterModule, RouterNameConstants } from 'customrouter';

@Entry
@Component
struct Index {
  @State exception?: string = "";
  @State entryHapRouter: NavPathStack = new NavPathStack()


  aboutToAppear(): void {
    if (!this.entryHapRouter) {
      this.entryHapRouter = new NavPathStack()
    }
    RouterModule.createRouter(RouterNameConstants.ENTRY_HAP, this.entryHapRouter)
  }

  @Builder
  routerMap(builderName: string, param: object) {
    RouterModule.getBuilder(builderName).builder(param)
  }

  build() {
    Navigation(this.entryHapRouter) {
      Column() {
        Button('跳转到 kuikly compose 页面')
          .onClick(() => {
            buildRouterModel(RouterNameConstants.ENTRY_HAP, BuilderNameConstants.HAR_kuiklycompose_kuiklyPage)
          })
      }
      .width('100%')
      .height('100%')
      .expandSafeArea([SafeAreaType.KEYBOARD]);
    }
    .title('NavIndex')
    .hideTitleBar(true)
    .navDestination(this.routerMap)
    .expandSafeArea([SafeAreaType.SYSTEM], [SafeAreaEdge.TOP, SafeAreaEdge.BOTTOM])
    .height('100%')
    .width('100%');
  }
}