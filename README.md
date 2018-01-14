# QAOnline
芝士达人提取题目apk，该项目只是用来提取芝士超人题目的`module`，具体怎样百度，建议配合`Github`上其他 py 代码，实现答题。
## 优点
- 速度快，与目前流行的ocr相比，减少了`adb pull`和 ocr 识别的时间
- free,不用再用百度的ocr识别
- 准确率高，虽然各种 ocr识别率已经很高了，但是还是有出错的时候。`QAOnline`所使用的方法提取题目的准确率几乎达到了100%。

## 缺点
需要自己 root android 手机并且安装 `xposed`

## 使用方法
- root 手机并且安装 xposed，这个自行 google 教程
- 修改代码里面`Constants`的`BASE_URL`为自己 pc 端接收题目的程序的 url
- clone，打包，安装到手机即可
    打包:进入主工程目录，运行 `./gradlew :app:assembleRelease`，
    打包结束后，直接安装`app/build/outputs/apk/debug/`下面的`app-release.apk`即可
- 最后在 xposed installer里面勾选`QAOnline`，重启即可

## 原理
原理很简单，hook 相应 app 代码，直接在长连接推送过来的地方拦截数据，然后发送请求到电脑

## 版权说明
 该项目仅供交流学习使用，严禁商业用途.



