# 硬核技能

## 1、倒排索引

参考：https://es-cn.blog.csdn.net/article/details/125846561

### 相关问题

- 全文检索是什么
- ES是如何检索数据的
- ES的检索流程是什么

### 基本原理

### 数据结构

### 压缩算法

## 2、Master选举

### 集群选举中重要的角色

- **主节点（active master）**：一般指的是活跃的主节点
- **候选节点（master node）**：具备`master`角色的节点默认都有“被选举权”，即是一个候选节点。候选节点可以参与Master选举过程
- **投票节点（master node）：**每个候选节点默认都有投票权，即每个候选节点默认都是一个投票节点，但如果配置了“voting_only ”的候选节点将只有选举权而没有被选举权，即仅投票节点。
- **专用主节点**：即 node.roles: [master]，一般指的是只保留master角色的候选节点。
- **仅投票节点**：即 node.roles: [master, voting_only] 指仅具备选举权，而被严格了被选举权的master节点。

### 节点失效监测

在源码的描述文件中有这样一段描述：

```
There are two fault detection processes running. The first is by the
master, to ping all the other nodes in the cluster and verify that they
are alive. And on the other end, each node pings to master to verify if
its still alive or an election process needs to be initiated
```

- NodesFaultDetection：即NodesFD，用于定期检查集群中的节点是否存活。
- MasterFaultDetection：即MasterFD，作用是定期检查Master节点是否存活。

### 何时触发选举

- 活跃节master节点数小于法定票数
- active master挂掉

### 选举流程

### 脑裂问题

- 何为脑裂：无主或多主
- 解决办法：discovery.zen.minimum_master_nodes=N/2+1，N为有效投票节点数。

## 3、写入原理

## 4、ES支持哪些类型的查询

此题目答案不唯一，按照不同的分类方式，答案也不一样

### 4.1 按语言划分

- Query DSL：Domain Specific Language

- Script：脚本查询

- Aggregations：聚合查询

- SQL查询

- EQL查询

### 4.2 按场景划分

#### 4.2.1 Query String

* **查询所有：**
  GET /product/_search
* **带参数：**
  GET /product/_search?q=name:xiaomi
* **分页：**
  GET /product/_search?from=0&size=2&sort=price:asc
* **精准匹配 exact value**
  GET /product/_search?q=date:2021-06-01
* **_all搜索 相当于在所有有索引的字段中检索**
  GET /product/_search?q=2021-06-01
  ```
  # 验证_all搜索
   PUT product
   {
     "mappings": {
       "properties": {
         "desc": {
           "type": "text", 
           "index": false
         }
       }
     }
   }
   # 先初始化数据
   POST /product/_update/5
   {
     "doc": {
       "desc": "erji zhong de kendeji 2021-06-01"
     }
   }
  ```

#### 4.2.2 全文检索-Fulltext query

```
GET index/_search
 {
   "query": {
     ***
   }
 }
```

* #### match：匹配包含某个term的子句
* #### match_all：匹配所有结果的子句
* #### multi_match：多字段条件
* #### match_phrase：短语查询，

#### 4.2.3 精准查询-Term query

* #### term：匹配和搜索词项完全相等的结果
  
  * term 和 match_phrase 区别:
    match_phrase 会将检索关键词分词, match_phrase的分词结果必须在被检索字段的分词中都包含，而且顺序必须相同，而且默认必须都是连续的
    term搜索不会将搜索词分词
  * term 和 keyword 区别
    term是对于搜索词不分词,
    keyword是字段类型,是对于source data中的字段值不分词
* #### terms：匹配和搜索词项列表中任意项匹配的结果
* #### range：范围查找

#### 4.2.4 过滤器-Filter

```
GET _search
 {
   "query": {
     "constant_score": {
       "filter": {
         "term": {
           "status": "active"
         }
       }
     }
   }
 }
```

* filter：query和filter的主要区别在： filter是结果导向的而query是过程导向。query倾向于“当前文档和查询的语句的相关度”而filter倾向于“当前文档和查询的条件是不是相符”。即在查询过程中，query是要对查询的每个结果计算相关性得分的，而filter不会。另外filter有相应的缓存机制，可以提高查询效率。

#### 4.2.5 组合查询-Bool query

**bool**：可以组合多个查询条件，bool查询也是采用more_matches_is_better的机制，因此满足must和should子句的文档将会合并起来计算分值

* **must**：必须满足子句（查询）必须出现在匹配的文档中，并将有助于得分。
* **filter**：过滤器 不计算相关度分数，cache☆子句（查询）必须出现在匹配的文档中。但是不像 must查询的分数将被忽略。Filter子句在[filter上下文](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-filter-context.html)中执行，这意味着计分被忽略，并且子句被考虑用于缓存。
* **should**：可能满足 or子句（查询）应出现在匹配的文档中。
* **must_not**：必须不满足 不计算相关度分数  not子句（查询）不得出现在匹配的文档中。子句在[过滤器上下文](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-filter-context.html)中执行，这意味着计分被忽略，并且子句被视为用于缓存。由于忽略计分，0因此将返回所有文档的分数。

**minimum_should_match**：参数指定should返回的文档必须匹配的子句的数量或百分比。如果bool查询包含至少一个should子句，而没有must或 filter子句，则默认值为1。否则，默认值为0

#### 4.2.6 地理位置搜索

- Geo_point
- Geo_shape

#### 4.2.7 嵌套查询

- Object

- Nested

- Join

### 4.3 按数据类型（准确度）划分

- 全文检索：match、match_phrase

- 精确查找：term、terms

- 模糊查询：suggester、模糊查询、通配符、正则查找

## 3、读写性能调优

参考：https://es-cn.blog.csdn.net/article/details/124104738

## 4、评分算法

参考：https://es-cn.blog.csdn.net/article/details/124811138

- TF-IDF
- BM25

## 5、Elasticsearch是什么？

### 5.1 `Elasticsearch`（后称为 ES ）是一个天生支持分布式的搜索、聚合分析和存储引擎。

### 5.2 民间叫法

- 搜索引擎
- 全文检索引擎
- 分部署文档系统
- 分布式数据库
- OLAP系统

### 5.3 新手速记

- **擅长与不擅长**：ES 最擅长从海量数据中检索少量相关数据，但不擅长单次查询大量数据（大单页）

- **写入实时性**：ES是OLAP系统，侧重于海量数据的检索，而写入实时性并不是很高，默认1秒，也就是ES缓冲区Buffer的刷新间隔时间，不了解Elasticsearch写入原理的同学可以暂时忽略。ES并非忽略了对写入性能的优化，而是“有意为之”，其原因就在于基于 ES 的写入机制，其写入实时性和大数据检索性能是一个二选一的行为。实际上生产环境中我们经常通过“**牺牲写入实时性**”的操作来换取更高更快的“数据检索”性能。

- **不支持事务**：正因为ES的写入实时性并不高，如果我们需要快速响应用户请求，我们常采取的手段就是使用缓存，但是在很多高并发的场景下，我们需要数据保持强一致性（如银行系统），因此需要使用具有ACID特性的数据库来支持，而MySQL就是一个比较好的选择。

- **极限性能**：PB（1PB = 1024TB = 1024²GB）级数据秒内响应。

  PS：有同学问题，老师我们公司集群几百万条数据，ES查询很慢，是不是达到瓶颈了。我只能告诉你，以“亿”为单位的数据量对 ES 来说都只是起点，远远谈不上瓶颈。

- **应用场景**：搜索引擎、垂直搜索、BI、GIthub、

## 6、MySQL为什么不适合做全文检索

# 软性技能

1. ## 简历要简洁美观、突出重点，HR是你的第一关。

2. ## 适当包装 ≠ 欺骗

3. ## 自信、大胆、勇敢！

4. ## 尽可能往自己会的方向引导

5. ## 如何面对不会的问题

6. ## 有不懂的很正常，面试官问的，也许只是他擅长的。

7. ## 注意自己的形象和言语谈吐

8. ## 真正的智者，知进退，懂隐忍。

9. ## 夯实基础，修炼内功，把功夫用在平时。

10. ## 最好有大厂履历，大厂经历是一张不错的名片。

11. ## 勿频繁跳槽。

12. ## 如何看待加班问题

13. ## 为什么从上一家公司离开

14. ## 考一个Elastic认证工程师证书

15. ## 职场法则

    

