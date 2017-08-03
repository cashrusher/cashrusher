# cashrusher

1. Maven的setting.xml文件的配置：

```xml
 <mirrors>
    <!-- mirror
     | Specifies a repository mirror site to use instead of a given repository. The repository that
     | this mirror serves has an ID that matches the mirrorOf element of this mirror. IDs are used
     | for inheritance and direct lookup purposes, and must be unique across the set of mirrors.
     |
    <mirror>
      <id>mirrorId</id>
      <mirrorOf>repositoryId</mirrorOf>
      <name>Human Readable Name for this Mirror.</name>
      <url>http://my.repository.com/repo/path</url>
    </mirror>
     -->
      <mirror>
      <id>maven.net.cn</id>
      <mirrorOf>central</mirrorOf>
      <name>central mirror in china</name>
      <url>http://maven.net.cn/content/groups/public</url>
    </mirror>
  </mirrors>
```
