package com.yt.serial;

/**
 *
 * 序列化：
 *
 * transient 关键字可以阻止属性序列化和反序列化
 *
 * 1.jdk自带序列化ObjectOutputStream和ObjectInputStream，
 * 特点：
 * 头部数据用来声明序列化协议、版本，用于高版本向后兼容
 * 对象数据主要包括类名、签名、属性名、属性类型及属性值、开头结尾数据
 * 存在对象引用、继承的情况下，递归遍历写对象逻辑
 *
 * 缺点：
 * 序列化后数组过大，且存在安全漏洞，一般不建议使用
 * 无法跨语言
 *
 * 2.JSON
 *  特点：
 *  文本型序列化框架，跨语言
 *  基于Http的RPC框架常用
 *
 *  缺点：
 *  序列化开销比较大，对于java需要通过反射生成对象
 *
 * 3.Hessian
 *  特点：
 *  动态类型、二进制、紧凑的、可跨语言移植的一种序列化框架，要比JDK、JSON更加紧凑，性能要比JDK、JSON序列化高效很多，而且生成的字节数也更小
 *
 * 缺点：
 * 主要集中在对Java一些常见类型不支持，LinkedHashMap、LinkedHashSet，
 * Byte/Short反序列化为Integer
 *
 *
 * 4.Kryo
 *
 * 5.XStream
 *
 * 6.protobuf
 * 需要启服务
 *
 * 7.Avro
 *
 * 8.Thrift
 */