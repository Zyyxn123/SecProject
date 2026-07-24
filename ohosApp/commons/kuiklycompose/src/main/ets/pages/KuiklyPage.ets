import { ErrorReason, KRRecord, Kuikly, KuiklyRenderBaseView } from '@kuikly-open/render';
import { BuilderNameConstants, RouterModule, RouterNameConstants } from 'customrouter';
import { kuiklyModuleType } from '../interface/kuiklyMoudle';
import { KuiklyViewDelegate } from '../kuikly/KuiklyViewDelegate';
import globalNativeManager from '../kuikly/MyNativeManager';
import { BusinessError } from '@kit.BasicServicesKit';

@Entry
@ComponentV2
export struct KuiklyPage {
  kuiklyViewDelegate = new KuiklyViewDelegate();
  pageName?: string;
  pageData?: KRRecord;
  @Local exception?: string = "";
  @Param @Once routerName?: Object = ''

  aboutToAppear() {
    AppStorage.setOrCreate<UIContext>("context", this.getUIContext());

    try {
      const res = (this.routerName as kuiklyModuleType).routerName
      console.log('KuiklyPageAboutToAppear: ' + res)
    } catch (e) {
      console.log('KuiklyPageAboutToAppearError: ' + JSON.stringify(e))
    }

    const params = this.routerName as Record<string, Object>;
    this.pageName = params?.pageName as string;
    this.pageData = params?.pageData as KRRecord;
  }

  build() {
    Stack() {
      Kuikly({
        pagerName: this.pageName ?? 'login',
        pagerData: this.pageData ?? {},
        delegate: this.kuiklyViewDelegate,
        nativeManager: globalNativeManager,
        onRenderException: (exception: Error, reason: ErrorReason) => {
          this.exception = `${exception.name}:\n${exception.message}`;
        },
      })
      if (this.exception) {
        Text(this.exception)
      }
    }
    .width('100%')
    .height('100%')
    .expandSafeArea([SafeAreaType.KEYBOARD])
  }

  onPageShow(): void {
    this.kuiklyViewDelegate.pageDidAppear();
  }

  onPageHide(): void {
    this.kuiklyViewDelegate.pageDidDisappear();
  }
}

@Builder
export function harBuilder(value: object) {
  NavDestination() {
    KuiklyPage({ routerName: value})
  }
  .hideTitleBar(true)
  .title('KuiklyPage')
  .onBackPressed(() => {
    RouterModule.pop(RouterNameConstants.ENTRY_HAP)
    return true
  })
}

const builderName = BuilderNameConstants.HAR_kuiklycompose_kuiklyPage
if (!RouterModule.getBuilder(builderName)) {
  const builder: WrappedBuilder<[object]> = wrapBuilder(harBuilder)
  RouterModule.registerBuilder(builderName, builder)
}
