import { BuilderNameConstants } from 'customrouter';

export { add } from './src/main/ets/utils/Calc';

export async function harInit(builderName: string): Promise<void> {
  switch (builderName) {
    case BuilderNameConstants.HAR_kuiklycompose_kuiklyPage:
      await import("./src/main/ets/pages/KuiklyPage")
      break
    default:
      break
  }
}