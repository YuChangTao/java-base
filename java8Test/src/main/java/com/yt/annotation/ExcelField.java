package com.yt.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({FIELD})
@Inherited
@Documented
public @interface ExcelField {

    /**
     * 是否检查必填
     *
     * @return
     */
    boolean required() default false;

    /**
     * 是否检查唯一
     *
     * @return
     */
    boolean unique() default false;

    /**
     * 检查是否指定格式<br/>
     * string\date\datetime\time\int\double\boolean\regex
     *
     * @return
     */
    String format() default "";

    /**
     * 处理属性
     *
     * @return
     */
    Class<?> handleField() default Class.class;

    /**
     * 字段排序号
     *
     * @return
     */
    int sort() default 0;
}
