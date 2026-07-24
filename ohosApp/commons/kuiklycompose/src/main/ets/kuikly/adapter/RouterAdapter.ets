import { IKRRouterAdapter, KRRecord } from '@kuikly-open/render';
import common from '@ohos.app.ability.common';
import { BuilderNameConstants, buildRouterModel, replaceRouterModel, RouterModule, RouterNameConstants } from 'customrouter';

interface type {
  pageName: string
  pageData: KRRecord
}
export class RouterAdapter implements IKRRouterAdapter {
  openPage(context: common.UIAbilityContext, pageName: string, pageData: KRRecord): void {
    const data: type = {
      pageName: pageName,
      pageData: pageData
    }
    buildRouterModel(
      RouterNameConstants.ENTRY_HAP,
      BuilderNameConstants.HAR_kuiklycompose_kuiklyPage,
      data,
    )
  }

  replacePage(context: common.UIAbilityContext, pageName: string, pageData: KRRecord): void {
    const data: type = {
      pageName: pageName,
      pageData: pageData
    }
    replaceRouterModel(
      RouterNameConstants.ENTRY_HAP,
      BuilderNameConstants.HAR_kuiklycompose_kuiklyPage,
      data,
    )
  }

  closePage(context: common.UIAbilityContext): void {
    RouterModule.pop(RouterNameConstants.ENTRY_HAP)
  }
}
