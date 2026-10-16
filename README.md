# Android 网络访问实验

## 实验目标

完成 Android 应用网络权限配置，验证网络安全配置对 HTTP 明文传输的拦截效果，完成 HTTPS 正常访问、HTTP 明文拦截、单域名放开明文的三组对比测试，验证请求参数动态变化效果。

## 运行方式

1. 使用 Android Studio 打开本项目，等待 Gradle 同步完成
2. 启动 Android 模拟器或连接真机设备
3. 点击顶部运行按钮安装并启动应用
4. 点击界面对应按钮，分别发起 HTTPS 与 HTTP 请求，查看响应结果

## 接口地址

- HTTPS 测试地址：[https://httpbin.org/get?room=A203](https://httpbin.org/get?room=A203)
- HTTP 测试地址：[http://httpbin.org/get?room=A203](http://httpbin.org/get?room=A203)

## 说明

HTTP 仅用于教学对比，实际生产环境中的服务应优先使用 HTTPS。