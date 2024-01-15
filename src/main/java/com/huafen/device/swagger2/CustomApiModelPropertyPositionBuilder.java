package com.huafen.device.swagger2;

import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import java.lang.reflect.Field;
import java.util.Optional;

import static springfox.documentation.schema.Annotations.findPropertyAnnotation;
import static springfox.documentation.swagger.schema.ApiModelProperties.findApiModePropertyAnnotation;

/**
 * - @ApiModelProperty注解 默认按照字段定义顺序排序
 * @author xiesq
 * @date 2023/6/16 10:51
 */
@Component
public class CustomApiModelPropertyPositionBuilder implements ModelPropertyBuilderPlugin {
    @Override
    public void apply(ModelPropertyContext context) {
        Optional<BeanPropertyDefinition> beanPropertyDefinitionOpt = context.getBeanPropertyDefinition();
        if (!beanPropertyDefinitionOpt.isPresent()) {
            return;
        }
        // 获取注解信息
        Optional<ApiModelProperty> annotation = getAnnotation(context);
        if (!annotation.isPresent() || annotation.get().position() != 0) {
            return;
        }
        // 获取字段和类信息
        AnnotatedField currentField = beanPropertyDefinitionOpt.get().getField();
        Field[] declaredFields = currentField.getDeclaringClass().getDeclaredFields();
        // 获取字段的定义顺序
        int index = indexOf(declaredFields, currentField);
        if (index != -1) {
            // 设置position属性      context.getSpecificationBuilder().position(indexOf)
            context.getBuilder().position(index);
        }

    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return SwaggerPluginSupport.pluginDoesApply(delimiter);
    }

    /**
     * 获取注解信息
     */
    private Optional<ApiModelProperty> getAnnotation(ModelPropertyContext context) {
        Optional<ApiModelProperty> annotation = Optional.empty();
        if (context.getAnnotatedElement().isPresent()) {
            annotation = findApiModePropertyAnnotation(context.getAnnotatedElement().get());
        }
        if (context.getBeanPropertyDefinition().isPresent()) {
            annotation = findPropertyAnnotation(context.getBeanPropertyDefinition().get(), ApiModelProperty.class);
        }
        return annotation;
    }

    /**
     * 获取字段的序号
     */
    private int indexOf(Field[] declaredFields, AnnotatedField field) {
        for (int i = 0; i < declaredFields.length; i++) {
            if (declaredFields[i].getName().equals(field.getName())) {
                return i;
            }
        }
        return -1;
    }

}


