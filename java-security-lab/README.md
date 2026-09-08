# Java Web 安全代码审计靶场

面向 Java 开发者的本地修复型测试项目，第一阶段包含 MyBatis 动态 SQL、JWT 信任边界和 SSRF 三关。

> 仅用于本地学习、公开靶场或明确授权环境。不要将练习代码作为生产安全组件。

## 运行与判题

要求 JDK 17、Maven 3.8+。

```bash
cd java-security-lab
mvn test
mvn -Pgrade test
```

普通测试用于确认环境，默认通过。`grade` 会运行安全判题；初始代码故意有缺陷，所以会失败。
只修改三个策略类，不要修改测试：

- `sql/OrderByPolicy.java`
- `jwt/JwtClaimsValidator.java`
- `ssrf/SsrfUrlPolicy.java`

## 审计记录模板

```text
Source：不可信数据从哪里进入
Flow：经过哪些方法或转换
Sink：最终影响什么危险操作
已有防护：做了什么校验
绕过原因：为什么没有闭环
修复方案：如何限制能力
回归测试：合法和恶意边界
```

## 第一关：MyBatis 动态排序

`toOrderBy` 的返回值会被 `${orderBy}` 直接拼进 SQL。

- 只允许业务字段 `createdAt`、`amount`、`status`；
- 分别映射为 `created_at`、`amount_cents`、`status`；
- 方向仅允许不区分大小写的 `asc`、`desc`；
- 非法或空参数抛出 `IllegalArgumentException`；
- 返回值必须完全由服务端常量组成。

## 第二关：JWT 信任边界

题目假设密码库已经完成签名数学验证，你负责判断令牌能否用于当前服务。

- 签名有效，算法固定为 `RS256`；
- `kid` 必须在可信密钥集合；
- 签发方精确匹配，受众包含当前服务；
- `sub` 非空且当前时间早于过期时间；
- 令牌权限版本等于服务端当前版本。

## 第三关：SSRF URL 策略

下载器会在首次请求和每次重定向前调用 `validateHop`。

- 只允许 HTTP、HTTPS，禁止 user-info，必须有主机名；
- 检查域名解析得到的每一个地址；
- 回环、链路本地、站点本地、任播、本机或组播地址均拒绝；
- 解析失败或返回空集合时拒绝。

完成标准：`mvn test` 和 `mvn -Pgrade test` 均显示 `BUILD SUCCESS`。
