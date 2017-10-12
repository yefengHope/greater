打开/关闭窗口左侧的边栏：Ctrl + B

增加一栏打开的文件窗口：Ctrl + \

在新的一栏里面打开文件：Ctrl + Enter （这个需要在左面的文件栏选择好文件，比较鸡肋）

通过名称搜索打开文件：Ctrl + P

保存文件：Ctrl + S （BTW，保存文件不是Ctrl + S的异端都要灭亡！）

自动保存文件：Ctrl + Shift + P （不是很喜欢idea的那种模式）

在目录下文件中全文搜索： Ctrl + Shift + F 

高级版的上一个功能： Ctrl + Shift + J

对编辑器输入命令：Ctrl + Shift + P （就当这个是vi的冒号的模式好了）

在最近打开的文件中切换：Ctrl + Tab

查找并且导航到符号：Ctrl + Shift + O

文件编辑窗口导航：Alt + ← 或者 Alt + → （这个倒是和idea很像）也可以通过 Ctrl + 数字键 导航

文件编辑

跳转到匹配的括号：Ctrl + Shift + ]

多行选择：Alt + Click （鼠标左键） 或者 Ctrl + Alt + ↑ 或 Ctrl + Alt + ↓

选择全部和当前光标所在的标识符同名的标识符：Ctrl + Shift + L 或者 Ctrl + F2 （代码重构反混淆神器）

代码提示：Ctrl + Space（比起idea那么多代码提示的快捷键好多了，嗯，可是为什么我要黑idea呢）

查看定义：F12 或者 Ctrl+ Click

转到定义：Ctrl + Alt + Click

查找符号：Ctrl + T

查找引用：Shift + F12 或 Alt + F12

重命名符号：F2

查看所有错误：Ctrl + Shift + M


>


官方快捷键大全：https://code.visualstudio.com/docs/customization/keybindings



Visual Studio Code是个牛逼的编辑器，启动非常快，完全可以用来代替其他文本文件编辑工具。又可以用来做开发，支持各种语言，相比其他IDE，轻量级完全可配置还集成Git感觉非常的适合前端开发。 所以我仔细研究了一下文档未来可能会作为主力工具使用。

主命令框
===

```


最重要的功能就是F1或Ctrl+Shift+P打开的命令面板了，在这个命令框里可以执行VSCode的任何一条命令，甚至关闭这个编辑器。
按一下Backspace会进入到Ctrl+P模式里
在Ctrl+P下输入>又可以回到Ctrl+Shift+P模式。
在Ctrl+P窗口下还可以

直接输入文件名，跳转到文件
? 列出当前可执行的动作
! 显示Errors或Warnings，也可以`Ctrl+Shift+M
: 跳转到行数，也可以Ctrl+G直接进入
@ 跳转到symbol（搜索变量或者函数），也可以Ctrl+Shift+O直接进入
@:根据分类跳转symbol，查找属性或函数，也可以Ctrl+Shift+O后输入:进入
# 根据名字查找symbol，也可以Ctrl+T
```
    
常用快捷
===

编辑器与窗口管理
-----
```
同时打开多个窗口（查看多个项目）
打开一个新窗口： Ctrl+Shift+N
关闭窗口： Ctrl+Shift+W
同时打开多个编辑器（查看多个文件）
新建文件 Ctrl+N
文件之间切换 Ctrl+Tab
切出一个新的编辑器（最多3个）Ctrl+\，也可以按住Ctrl鼠标点击Explorer里的文件名
左中右3个编辑器的快捷键Ctrl+1 Ctrl+2 Ctrl+3
3个编辑器之间循环切换 Ctrl+`
编辑器换位置，Ctrl+k然后按Left或Right
代码编辑

格式调整
代码行缩进Ctrl+[ Ctrl+]
Ctrl+C Ctrl+V如果不选中，默认复制或剪切一整行
代码格式化：Shift+Alt+F，或Ctrl+Shift+P后输入format code
上下移动一行： Alt+Up 或 Alt+Down
向上向下复制一行： Shift+Alt+Up或Shift+Alt+Down
在当前行下边插入一行Ctrl+Enter
在当前行上方插入一行Ctrl+Shift+Enter
光标相关
移动到行首：Home
移动到行尾：End
移动到文件结尾：Ctrl+End
移动到文件开头：Ctrl+Home
移动到定义处：F12
定义处缩略图：只看一眼而不跳转过去Alt+F12
移动到后半个括号 Ctrl+Shift+]
选择从光标到行尾Shift+End
选择从行首到光标处Shift+Home
删除光标右侧的所有字Ctrl+Delete
Shrink/expand selection： Shift+Alt+Left和Shift+Alt+Right
Multi-Cursor：可以连续选择多处，然后一起修改，Alt+Click添加cursor或者Ctrl+Alt+Down 或 Ctrl+Alt+Up
同时选中所有匹配的Ctrl+Shift+L
Ctrl+D下一个匹配的也被选中(被我自定义成删除当前行了，见下边Ctrl+Shift+K)
回退上一个光标操作Ctrl+U
重构代码
找到所有的引用：Shift+F12
同时修改本文件中所有匹配的：Ctrl+F12
重命名：比如要修改一个方法名，可以选中后按F2，输入新的名字，回车，会发现所有的文件都修改过了。
跳转到下一个Error或Warning：当有多个错误时可以按F8逐个跳转
查看diff 在explorer里选择文件右键 Set file to compare，然后需要对比的文件上右键选择Compare with 'file_name_you_chose'.
查找替换
查找 Ctrl+F
查找替换 Ctrl+H
整个文件夹中查找 Ctrl+Shift+F
匹配符：
* to match one or more characters in a path segment
? to match on one character in a path segment
** to match any number of path segments ,including none
{} to group conditions (e.g. {**/*.html,**/*.txt} matches all html and txt files)
[] to declare a range of characters to match (e.g., example.[0-9] to match on example.0,example.1, …
显示相关

全屏：F11
zoomIn/zoomOut：Ctrl + =/Ctrl + -
侧边栏显/隐：Ctrl+B
侧边栏4大功能显示：
Show Explorer Ctrl+Shift+E
Show SearchCtrl+Shift+F
Show GitCtrl+Shift+G
Show DebugCtrl+Shift+D
Show OutputCtrl+Shift+U
其他

自动保存：File -> AutoSave ，或者Ctrl+Shift+P，输入 auto
修改默认快捷键

File -> Preferences -> Keyboard Shortcuts

修改keybindings.json，我的显示在这里C:\Users\Administrator\AppData\Roaming\Code\User\keybindings.json

```

```
// Place your key bindings in this file to overwrite the defaults
[
    //ctrl+space被切换输入法快捷键占用
    {
        "key": "ctrl+alt+space",
        "command": "editor.action.triggerSuggest",
        "when": "editorTextFocus"
    },
    // ctrl+d删除一行
    {
        "key": "ctrl+d",
        "command": "editor.action.deleteLines",
        "when": "editorTextFocus"
    },
    {
        "key": "ctrl+shift+k", //与删除一行的快捷键互换了：）
        "command": "editor.action.addSelectionToNextFindMatch",
        "when": "editorFocus"
    },
    //ctrl+shift+/多行注释
    {
        "key":"ctrl+shift+/",
        "command": "editor.action.blockComment",
        "when": "editorTextFocus"
    }
]
```

插件

新版本支持插件安装了

插件市场 https://marketplace.visualstudio.com/#VSCode

安装插件

F1 输入 extensions



点击第一个开始安装或升级，或者也可以  Ctrl+P 输入 ext install进入
点击第二个会列出已经安装的扩展，可以从中卸载