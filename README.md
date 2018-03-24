# java组编码规范
## java代码格式规范
- **参考[阿里java代码规范](https://github.com/xuguanhua/xmtgzs/blob/master/%E9%98%BF%E9%87%8C%E5%B7%B4%E5%B7%B4%2BJava%2B%E5%BC%80%E5%8F%91%E6%89%8B%E5%86%8C.pdf)**
## Pull Request要求

- **代码格式** 提交前请按项目风格进行格式化。

- **必须添加测试！** - 提交前必须确认代码可运行。

- **记得更新文档** - 保证`README.md`以及其他相关文档及时更新，和代码的变更保持一致性。

- **创建feature分支** - 最好不要从你的master分支提交 pull request。

- **一个feature提交一个pull请求** - 如果你的代码变更了多个操作，那就提交多个pull请求吧。

- **清晰的commit历史** - 保证你的pull请求的每次commit操作都是有意义的。如果你开发中需要执行多次的即时commit操作，那么请把它们放到一起再提交pull请求。**详情请了解git rebase**

## Commit message 规范

规定格式如下：

```
<type>(<scope>): <subject> #issue_number

<description>
```

其中，type、scope、subject是必需的，description 可以省略。不管是哪一个部分，任何一行都不得超过72个字符（或100个字符）。这是为了避免自动换行影响美观。

#### type 部分取值说明

type用于说明 commit 的类别，只允许使用下面7个标识。

- **feature:** 新功能（feature）
- **fix:** 修补bug、style等
- **docs:** 文档（documentation）
- **refactor:** 重构（即不是新增功能，也不是修改bug的代码变动）
- **test:** 增加测试
- **chore:** 构建过程或辅助工具的变动

#### scope 部分取值说明

一般为项目功能模块、组件的名字，用来描述本次 commit 影响的范围，比如 [node commits](https://github.com/nodejs/node/commits/master) 、[go commits](https://github.com/golang/go/commits/master) 。嵌套层级结构可以用 / 表示，如 net/http。影响多个模块、组件可以用 , 隔开（不加空格），如 net/http,cmd。后加入项目的新成员应遵循已有的 scope 约定（通过 git log 可以查看某个文件的提交历史），不要自己编造。使用首字母小写的驼峰命名。除具体的模块、组件名之外，可以使用 base 表示基础结构、框架相关的改动，用 misc 表示杂项改动，用 all 表示大范围重构。

#### subject 部分

subject是 commit 目的的简短描述，50 个字符左右的简要说明，首字母小写，通常是动宾结构，描述做了什么事情，动词用一般现在时，禁止出现 update code ， fix bug 等无实际意义的描述，好的例子： select connector by sorting free memory （不需要形如 update about how to select connector ... 的啰嗦写法）, fix success tip can not show on IE8 （不需要形如 fix bug of ... 的啰嗦写法）

- 以动词开头，使用第一人称现在时，比如change，而不是changed或changes
- 尽量使用简单句保证简洁性
- 第一个字母小写
- 结尾不加句号（.）
- 通过翻译检测工具确认英文的正确性和可读性

#### Commit message 延伸阅读

- [Conventional Changelog](https://github.com/ajoslin/conventional-changelog)
- [Commit message 和 Change log 编写指南](http://www.ruanyifeng.com/blog/2016/01/commit_message_change_log.html)

