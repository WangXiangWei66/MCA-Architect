## 1、什么是 TCP 网络分层

* **应用层** ：应用程序之间如何相互传递报文，比如 HTTP 协议。例如 GET/HTTP/1.1。
* **传输层** ：为两台主机之间的 “应用进程” 提供端到端的逻辑通信，比如 TCP 协议。会增加 TCP 头部，包含端口号、序列号等。如 TCP 头 GET/HTTP/1.1。
* **网络互连层** ：提供了主机到主机的通信，将传输层产生的数据包封装成分组数据包发送到目标主机，并提供路由选择的能力。
* IP 协议是网络层的主要协议，TCP 和 UDP 都用 IP 协议作为网络层协议。这一层会增加 IP 头部，包含源 IP 地址等。如 IP 头 TCP 头 GET/HTTP/1.1。
* **网络访问层** ：也有说法叫做网络接口层，以太网、Wifi、蓝牙工作在这一层，提供了主机连接到物理网络需要的硬件和相关的协议。会增加以太网头部，包含 MAC 地址等。如以太网头 IP 头 TCP 头 GET /HTTP/1.1。
* ![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/58551/1751208661080/9d4a42e194cd4ee49235e6777dd88815.png)

### 分层的好处

* 各层独立：限制了依赖关系的范围，各层之间使用标准化的接口，各层不需要知道上下层是如何工作的，增加或者修改一个应用层协议不会影响传输层协议。
* 灵活性更好：比如路由器不需要应用层和传输层，分层以后路由器就可以只用加载更少的几个协议层。
* 易于测试和维护：提高了可测试性，可以独立的测试特定层，某一层有了更好的实现可以整体替换掉。
* 能促进标准化：每一层职责清楚，方便进行标准化。

## 2、TCP 的三次握手中为什么是三次？为什么不是两次、四次？

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/58551/1751208661080/8e6d1c00937743af8a9f1d5509d55f02.png)

## 3、TCP 的四次挥手为什么是四次？为什么不能是三次？

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/58551/1751208661080/b4ccffda95dc48719bc308b0b6bd970d.png)

## 4、为什么 SYN/FIN 不包含数据却要消耗一个序列号？

凡是需要对端确认的，一定消耗 TCP 报文的序列号。SYN 和 FIN 需要对端的确认，所以需要消耗一个序列号。

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/58551/1751208661080/18b413f6adce42568286f978822c68b8.png)

## 5、什么是半连接队列？什么是 SYN Flood 攻击？

客户端大量伪造 IP 发送 SYN 包，服务端回复的 ACK+SYN 会发送到未知的 IP 地址，势必会造成服务器大量的连接处于SYN_RCVD状态，而服务器的半连接队列满。也会出现无法正常请求的情况。

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/58551/1751208661080/2568ca1036e4404e908439355111417f.png)

## 6、说说 TCP 快速打开 (TFO) 的原理！

TCP 快速打开（TCP Fast Open）

TFO是在原来 TCP 协议上的扩展协议，其主要原理是在发送第一个 SYN 包的时候就开始传数据，但要求当前客户端之前已经完成过 “正常” 的三次握手。

* **快速打开分两个阶段**

  * **请求 Fast Open Cookie** ：发送端发送 SYN 包（捎带 Cookie request），接收端生成 Cookie 值并通过 SYN + ACK + Cookie 传给发送端，发送端将 Cookie 存储在本地并发送 ACK。
  * ![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/58551/1751208661080/0e22ddc17655467492edda6fa1716ea2.png)
  * **真正开始 TCP Fast Open** ：发送端发送包含 SYN + Cookie、数据（如 HTTP 请求）的数据包，接收端校验 Cookie 合法性后，发送 SYN + ACK，并发送 HTTP 响应数据包。
  * ![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/58551/1751208661080/c4828d1ea56249dc9c36716f71aa37a5.png)
* **TCP Fast Open 的优势** ：最显著的优点是可以利用握手去除一个往返 RTT，还可以防止 SYN-Flood 攻击之类的。

  ![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/58551/1751208661080/7978c44aed1047929fe62bb550bda43a.png)

## 7、TCP 报文中的时间戳有什么作用？

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/58551/1751208661080/1c4089b56f294a36a731b50a6745132e.png)

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/58551/1751208661080/06d416145f1b419d866897eaa1884264.png)

TCP Timestamps Option 由四部分构成：类别（kind）、长度（Length）、发送方时间戳（TS value）、回显时间戳（TS Echo Reply）。

* 主要解决两大问题：
  * 计算往返时延 RTT（Round-Trip Time）：在启用 Timestamps 选项以后，无论是正常确认包，还是重传确认包，都可以通过 TSval 和 TSecr 计算出 RTT。

    ![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/58551/1751208661080/e26e0fc32aa948f88cd6260f56bc7056.png)
  * 防止序列号的回绕问题：TCP 的序列号用 32bit 来表示，因此在2^32字节的数据传输后系列号就会溢出回绕。tcp的窗口经过窗口缩放可以最高到1GB（2^30），在高速网络中，了序列号在很短的时间就会被重复利用

    ![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/58551/1751208661080/16f6b993a9be4118b1c3b09163d9b9d2.png)

    ![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/58551/1751208661080/e7150c09c94b4e39b14b694a47fe302c.png)
  * 假设发送了 6 个数据包，每个数据包的⼤⼩为 1GB，第 5 个包序列号发⽣回绕。
    第 2 个包因为某些原因延迟导致重传，但没有丢失到时间t7 才到达。
    这个迷途数据包与后⾯要发送的第 6 个包序列号完全相同，如果没有⼀些措施进⾏区分，将会造成数据的紊乱。
    有Timestamps的存在，迷途数据包与第 6 个包可以区分。

## 8、TCP 的超时重传时间是如何计算的？

TCP 具有超时重传机制，即间隔一段时间没有等到数据包的回复时，重传这个数据包，这个重传间隔叫做超时重传时间（Retransmission TimeOut，简称 RTO）。

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/58551/1751208661080/d3e3d79646994dd5a2bb82b7e1403430.png)

* **经典方法（适用 RTT 波动较小的情况）**

  往返时延RTT(Round-TripTime)
  ⼀个最简单的想法就是取平均值，⽐如第⼀次RTT为 500ms，第⼆次RTT为 800ms，那么第三次发送时，各让⼀步取平均值RTO为 650ms。
  经典算法引⼊了「平滑往返时间」（Smoothedroundtriptime，SRTT）：经过平滑后的RTT的值，每测量⼀次RTT就对SRTT作⼀次更新计算。
* ![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/58551/1751208661080/6703f62ce54d439c902a2c983937e32d.png)

α是平滑因⼦，建议值是0.8 ~ 0.9，假设平滑因⼦α= 0.8，SRTT= 80%的原始值+ 20%的新采样RTT值

当α趋近于 1 时：SRTT越接近上⼀次的SRTT值，与新的RTT值的关系越⼩，表现出来就是对短暂的时延变化越不敏感。

当α趋近于 0 时，1 -α趋近于 1，SRTT越接近新采样的RTT值，与旧的SRTT值关系越⼩，表现出来就是对时延变化更敏感，能够更快速的跟随时延的变化⽽变化

## 9、能不能说一说 TCP 的流量控制？

对于发送端和接收端而言，TCP 需要把发送的数据放到发送缓存区，将接收的数据放到接收缓存区。流量控制就是通过接收缓存区的大小，控制发送端的发送，如果对方的接收缓存区满了，就不能再继续发送了。

为了控制发送端的速率，接收端会告知客户端⾃⼰接收窗⼝（rwnd），也就是接收缓冲区中空闲的部分。

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/58551/1751208661080/950b5d1cea464eeeb16268b8098c08a6.png)

接收窗口：接收缓冲区中空闲的部分

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/58551/1751208661080/2a9bb9ec57e74476a8f40e4237a93447.png)

发送端的数据包状态：

已发送且已确认

已发送但未确认

未发送但接收端可以接收(接收端有空间接收)

未发送且不可以发送(接收端没空间接收)

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/58551/1751208661080/741f9d5aea974dee8190197f84c15bbe.png)

发送端速度比较慢的情况

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/58551/1751208661080/aede8fb3f2c74f90a01ea103ef66327f.png)

发送端速度比较快的情况：

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/58551/1751208661080/f2a6195ca8d7430ca74ad2d126fb8fe6.png)

## 10、如何理解 TCP 的 keep-alive 的原理？

当一个 TCP 连接上通信双方都不向对方发送数据时，TCP 连接就不会有任何数据交换。

假设应⽤程序是⼀个web服务器，客户端发出三次握⼿以后故障宕机或被踢掉⽹线，对于web服务器⽽已，下⼀个数据包将永远⽆法到来，但是它⼀⽆所知。

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/58551/1751208661080/7d3d0ff98ac84e38bcae57a060b04dfe.png)

TCP协议的设计者考虑到了这种检测⻓时间死连接的需求，于是乎设计了keepalive机制。

它的作⽤就是探测对端的连接有没有失效，通过定时发送探测包来探测连接的对端是否存活，不过默认情况下需要 7200s没有数据包交互才会发送keepalive探测包，往往这个时间太久了，我们熟知的很多组件都没有开启keepalive特性，而是选择在应用层做心跳机制

## 11、聊一聊 TCP 中的端口号

端口号（Port）

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/58551/1751208661080/a53add864a1f43ebb4a4de9ff31c4022.png)

TCP 用两字节的整数来表示端口，一台主机最大允许 65536 个端口号。

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/58551/1751208661080/a469e71abe274244ba91a32b363d0f90.png)

* **端口号与网络分层** ：在传输层增加 TCP 头部时包含源端口和目标端口等信息。
* 分类：
  * 熟知端口号：范围 0~1023，如 HTTP 为 80，HTTPS 为 443，SSH 为 22。
  * 已登记的端口号：范围 1024~49151，如 MySQL 为 3306，Redis 为 6379，MongoDB 为 27017。
  * 临时端口号：范围 49152~65535。

## 12、TCP 场景问题 1

A、B 两个主机之间建立了一个 TCP 连接，A 主机发给 B 主机两个 TCP 报文，大小分别是 500 和 300，第一个报文的序列号是 200，那么 B 主机接收两个报文后，返回的确认号是 200 + 500 + 300 = 1000。

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/58551/1751208661080/e1d8bef241ae44ea9d9d9dae985c6ceb.png)

## 13、TCP 场景问题 2

收到 IP 数据包解析后，他怎么直到这个分组应该投递到上层的哪一个协议（UDP或TCP）

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/58551/1751208661080/c1a3a038615143e7a58aa14c67b65793.png)

IP头信息：

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/58551/1751208661080/4441e41a1f4f4abda4f5cf9d68d5b49d.png)

协议:区分IP协议上的上层协议。在Linux系统的/etc/protocols⽂件中定义了所有上层协议对应的协议字段，ICMP为1，TCP为6，UDP为17

## 14、TCP 场景问题 3

TCP 提供字节流服务，收发双方不保持记录的边界，应用程序应该如何提供他们自己的记录标识呢？

![image.png](https://fynotefile.oss-cn-zhangjiakou.aliyuncs.com/fynote/fyfile/58551/1751208661080/60c40c7dcadc472d870f8340836150f2.png)

可以使用自己约定的规则来表示消息的边界，比如有一些使用回车 + 换行（“\r\n”），例如 Redis 的通信协议（RESP protocol）。

## 15、讲一讲 telnet 的用法

* **检查端口是否打开** ：telnet 的一个最大作用就是检查一个端口是否处于打开，使用的命令是 telnet [domainname or ip] [port]，这条命令能告诉我们到远端 server 指定端口的网络连接是否可达。
* **telnet 发送 http 请求** ：执行 telnet [www.baidu.com](https://www.baidu.com/) 80，粘贴下面的文本（注意总共有四行，最后两行为两个空行）：

```plaintext
GET /HTTP/1.1
Host:www.baidu.com
```

## 16、讲一讲 netstat 的用法

netstat 命令用于显示各种网络相关信息，常见参数：

* -a（all）：显示所有选项，默认不显示 LISTEN 相关。
* -t（tcp）：仅显示 tcp 相关选项。
* -u（udp）：仅显示 udp 相关选项。
* -n：拒绝显示别名，能显示数字的全部转化成数字。
* -l：仅列出有在 Listen（监听）的服务状态。
* -p：显示建立相关链接的程序名。
* -r：显示路由信息，路由表。
* -e：显示扩展信息，例如 uid 等。
* -s：按各个协议进行统计。
* -c：每隔一个固定时间，执行该 netstat 命令。

## 17、讲一讲 tcpdump 的用法

tcpdump 是一个命令行的网络流量分析工具，功能非常强大，一般用来抓 TCP 的包。

## 18、讲一讲 wireshark 的用法

tcpdump 是命令行程序，对 linux 服务器比较友好，简单快速适合简单的文本协议的分析和处理。wireshark 有图形化的界面，分析功能非常强大，不仅仅是一个抓包工具，且支持众多的协议。wireshark 可以演示网络分层。

## 19、TCP 和 UDP 的区别？

* TCP 是面向连接的、可靠的、基于字节流的传输层协议；UDP 是面向无连接的传输层协议。
* 面向连接：所谓的连接，指的是客户端和服务器的连接，在双⽅互相通信之前，TCP需要三次握⼿建⽴连接，⽽UDP没有相应建⽴连接的过程。
* 可靠性：TCP花了⾮常多的功夫保证连接的可靠，这个可靠性体现在哪些⽅⾯呢？

1)TCP有状态：TCP会精准记录哪些数据发送了，哪些数据被对⽅接收了，哪些没有被接收到，⽽且保证数据包按序到达，不允许半点差错

2)TCP可控制：意识到丢包了或者⽹络环境不佳，TCP会根据具体情况调整⾃⼰的⾏为，控制⾃⼰的发送速度或者重发

## 20、如果要你来设计一个 QQ，在网络协议上你会考虑如何设计？

登陆采⽤TCP协议和HTTP协议，你和好友之间发送消息，主要采⽤UDP协议，内⽹传⽂件采⽤了P2P技术。

总的来说：

1.登陆过程，客户端client采⽤TCP协议向服务器server发送信息，HTTP协议下载信息。登陆之后，会有⼀个TCP连接来保持在线状态。

2.和好友发消息，客户端client采⽤UDP协议，但是需要通过服务器转发。腾讯为了确保传输消息的可靠，采⽤上层协议来保证可靠传输。如果消息发送失败，客户端会提示消息发送失败，并可重新发送。

3.如果是在内⽹⾥⾯的两个客户端传⽂件，QQ采⽤的是P2P技术，不需要服务器中转。
