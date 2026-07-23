import { demo } from 'kuiklycompose'

@Entry
@Component
struct Index {
  @State exception?: string = "";

  build() {
    Column() {
      Button('add awfwafsa')
      demo()
      if (this.exception) {
        Text(this.exception)
      }
    }
    .width('100%')
    .height('100%')
    .expandSafeArea([SafeAreaType.KEYBOARD])
  }

}