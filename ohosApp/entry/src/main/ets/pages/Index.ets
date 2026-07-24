import { BuilderNameConstants, buildRouterModel, RouterModule, RouterNameConstants } from 'customrouter';

@Entry
@Component
struct Index {
  @State exception?: string = "";
  @State entryHapRouter: NavPathStack = new NavPathStack()
  // @StorageProp('bottomRectHeight')
  // bottomRectHeight: number = 0;
  // @StorageProp('topRectHeight')
  // topRectHeight: number = 0;


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
            // this.getUIContext().getPromptAction().showToast()
            buildRouterModel(RouterNameConstants.ENTRY_HAP, BuilderNameConstants.HAR_kuiklycompose_kuiklyPage, new Object({
              routerName: 'login'
            }))
          })
          .margin({ top: 40 })
      }
      .width('100%')
      .height('100%')
      .expandSafeArea([SafeAreaType.KEYBOARD])
    }
    // .onAppear(() => {
    //   buildRouterModel(RouterNameConstants.ENTRY_HAP, BuilderNameConstants.HAR_kuiklycompose_kuiklyPage, new Object({
    //     routerName: 'LoginPage'
    //   }))
    // })
    .title('NavIndex')
    .hideTitleBar(true)
    .navDestination(this.routerMap)
    .expandSafeArea([SafeAreaType.SYSTEM], [SafeAreaEdge.TOP, SafeAreaEdge.BOTTOM])
    .height('100%')
    .width('100%')
    // .padding({
    //   top: this.getUIContext().px2vp(this.topRectHeight),
    //   bottom: this.getUIContext().px2vp(this.bottomRectHeight)
    // })
  }
}