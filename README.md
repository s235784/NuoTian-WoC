# NuoTian-WOC
SAST.2021 后端 & Java WoC



- [x] JWT鉴权
- [x] 全局异常处理
- [x] 全局统一返回
- [x] 前端网页



## 网页

| 地址   | 说明     |
| ------ | -------- |
| /login | 登录界面 |
| /home  | 主页     |



## 功能

本项目详细的API文档和示例请看[Apifox](https://www.apifox.cn/apidoc/shared-87a9a886-ffd6-46aa-ba1e-32b9534e4180/api-10154479)



| 名称       | 地址                        | 备注                                        |
| :--------- | --------------------------- | ------------------------------------------- |
| 注册       | /user/register              |                                             |
| 登录       | /user/login                 |                                             |
| 获取用户数 | /admin/getAmount            | 仅能由管理员请求                            |
| 删除用户   | /admin/deleteAccountBy{key} | 仅能由管理员请求，key值可以为“Name”或“Mail” |
| 添加用户   | /admin/addAccount           | 仅能由管理员请求                            |



### 注册

请求参数

| 参数名   | 位置  | 必填 | 说明 |
| -------- | ----- | ---- | ---- |
| username | query | 是   |      |
| password | query | 是   |      |
| email    | query | 是   |      |

返回示例

<img src="https://raw.githubusercontent.com/s235784/NuoTian-WoC/main/img/register-success.png" alt="register-success" style="zoom: 80%;" />

### 登录

请求参数

| 参数名   | 位置  | 必填 | 说明                  |
| -------- | ----- | ---- | --------------------- |
| password | query | 是   |                       |
| username | query | 否   | username与email二选一 |
| email    | query | 否   |                       |

返回示例

<img src="https://raw.githubusercontent.com/s235784/NuoTian-WoC/main/img/login-success.png" alt="login-success" style="zoom:80%;" />

### 获取用户数

请求参数

| 参数名 | 位置   | 必填 | 说明 |
| ------ | ------ | ---- | ---- |
| token  | header | 是   |      |

返回示例

<img src="https://raw.githubusercontent.com/s235784/NuoTian-WoC/main/img/count-success.png" alt="count-success" style="zoom: 80%;" />

### 删除用户

``` 注意
管理员能删除角色为“用户”的账号
超级管理员能删除角色为“用户”和“管理员”的账号
```

请求参数

| 参数名 | 位置   | 必填 | 说明           |
| ------ | ------ | ---- | -------------- |
| key    | path   | 是   | 支持Name或Mail |
| value  | query  | 是   | 与key对应的值  |
| token  | header | 是   |                |

返回示例

<img src="https://raw.githubusercontent.com/s235784/NuoTian-WoC/main/img/delete-success.png" alt="delete-success" style="zoom:80%;" />

### 添加用户

```注意
管理员能添加角色为“用户”的账号
超级管理员能添加角色为“用户”和“管理员”的账号
```

请求参数

| 参数名   | 位置   | 必填 | 说明                             |
| -------- | ------ | ---- | -------------------------------- |
| username | query  | 是   |                                  |
| email    | query  | 是   |                                  |
| password | query  | 是   |                                  |
| role     | query  | 否   | 用户 管理员分别对应0和1，默认为0 |
| token    | header | 是   |                                  |

返回示例

<img src="https://raw.githubusercontent.com/s235784/NuoTian-WoC/main/img/add-success.png" alt="add-success" style="zoom:80%;" />

## License
``` license
 Copyright 2022, NuoTian       

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
