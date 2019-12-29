# klotski

华容道，源自三国时期，曹操赤壁之战后败走华容道，最后被关羽放走的典故。其衍生的此款游戏属于一种滑块游戏。参阅 [维基百科](https://zh.wikipedia.org/zh-cn/%E8%8F%AF%E5%AE%B9%E9%81%93_(%E9%81%8A%E6%88%B2))，[百度百科](https://baike.baidu.com/item/%E5%8D%8E%E5%AE%B9%E9%81%93/23619)。

华容道题解，可抽象为 **非双方对弈的** *(不同于五子棋)*、**无唯一解的** *(可走不同路径，可扩展为曹操必走某处)*、**局部目标固定的** *(只要求曹操出来，可扩展为要求关羽或小兵位置)*、**移动滑块** *(可扩展为支持移形换位——跳着移动)*、**求最短路径** *(用时最少未必是最短路径)* 的问题。

仓库包含两部分内容
1. 借位运算解构棋子移动，BFS求解最短路径问题【已完结】
    - [题解博客(solver blog)](http://blog.lzh.today/klotski-solver/)
    - [题解源码(solver source)](https://github.com/lzhlyle/klotski/blob/master/src/main/java/com/lzhlyle/klotski/solver/QuickSolver4Blog.java)
    - 题解过程 [题解源码文件夹](https://github.com/lzhlyle/klotski/tree/master/src/main/java/com/lzhlyle/klotski/solver)
2. 从面向对象角度分析游戏，搭建可扩展性更强的华容道【持续更新】
    - [扩展性程序源码](https://github.com/lzhlyle/klotski/tree/master/src/main/java/com/lzhlyle/klotski)
    - [分析过程博客](https://www.cnblogs.com/lzhlyle/p/klotski.html)
