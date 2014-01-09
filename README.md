AndoridUtils
============

Android开发中常用到的一些工具类

##Logger
Log输出类, 可以设置Log输出级别:

    Logger.setLevel(Logger.INFO);

有5个级别, `VERBOSE`, `DEBUG`, `INFO`, `WARN`, `ERROR`.

可以禁用Log：

    Logger.disableLog

当通过`setLevel`设置Log输出级别时会启用Log.

##Toast
如果只是要显示一个简单的广本,可以用`ToastHelper.show`系列方法, 如果要自定义Toast, 比如设置Gravity, 设置Toast广本颜色等, 可以使用`ToastHelper.Builder`类.

向`ToastHelper.show`方法或`ToastHelper.Builder`传入`cancelPrevious`参数可以在这个Toast显示之前取消掉上一个Toast(如果有Toast正在显示的话), **前提是当前正在显示的Toast是通过`ToastHelp.show`或`ToastHelper.Build.show`显示出来的**.