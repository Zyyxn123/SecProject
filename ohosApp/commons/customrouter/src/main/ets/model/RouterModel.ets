import { RouterModule } from '../utils/RouterModule'
import { hilog } from '@kit.PerformanceAnalysisKit'

// Routing information.
// 路由数据结构
export class RouterModel {
  // Route page alias, in the form：${bundleName}_${pageName}.
  builderName: string = ""
  // Routing Stack Name.
  routerName: string = ""
  // Parameters that need to be transferred to the page.
  param?: object = new Object()
}

// Create routing information and put it into the routing stack table.
// 构造 RouterModel 实例并调用 RouterModule.push(router) 发起跳转
export function buildRouterModel(routerName: string, builderName: string, param?: object) {
  let router: RouterModel = new RouterModel()
  router.builderName = builderName
  router.routerName = routerName
  router.param = param
  RouterModule.push(router)
}

