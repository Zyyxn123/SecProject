import hilog from '@ohos.hilog'

class Logger {
  private domain: number
  private prefix: string
  private format: string = '%{public}s, %{public}s'

  constructor(prefix: string, domain: number = 0x0000) {
    this.prefix = prefix
    this.domain = domain
  }

  /**
   * DEBUG：最低级别，用于开发调试阶段输出详细的程序运行信息（如变量值、流程节点）
   * INFO：常规运行状态记录（如服务启动、关键操作完成）
   * WARN：不影响程序继续运行但需关注的潜在问题（如低内存警告）
   * ERROR：严重错误事件（如网络连接失败、数据校验异常）
   */

  debug(...args: string[]): void {
    hilog.debug(this.domain, this.prefix, this.format, args)
  }

  info(...args: string[]): void {
    hilog.info(this.domain, this.prefix, this.format, args)
  }

  warn(...args: string[]): void {
    hilog.warn(this.domain, this.prefix, this.format, args)
  }

  error(...args: string[]): void {
    hilog.error(this.domain, this.prefix, this.format, args)
  }
}

export default new Logger('[CustomRouter]')

