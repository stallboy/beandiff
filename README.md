beandiff
========

说明
-----
bean整个类定义都是生成出来的。的diff，applyPatch函数都是要生成的
bean diff生成patch，然后marshal，通过网络发过去，另一端applyPatch生效。

目的
-----
客户端订阅服务器视图后，如果服务器有变化，自动diff，然后同步patch过去。

实现
-----
对primitive type,bean,map,set算diff都简单
对list则只简单比较相同的头尾，然后发中间序列。而不会使用通用的longest common sequence算法。
比如 http://www.xmailserver.org/diff2.pdf

因为我们的目的就是充分利用结构化信息来简化diff。已达到高效率。