package com.zimug.imooc.websky.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.zimug.imooc.websky.common.mapper.SysDictionaryMapper;
import com.zimug.imooc.websky.common.model.SysDictionary;
import com.zimug.imooc.websky.common.model.SysDictionaryExample;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class FastjsonConfig implements CommandLineRunner {

    @Resource
    SysDictionaryMapper sysDictionaryMapper;

    private List<SysDictionary> dicts;


    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue
        );
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        ValueFilter valueFilter = new ValueFilter() {
            public Object process(Object object, String property, Object value) {
                if (null == value) {
                    value = "";
                }
                //数据字典的翻译
                Map<String,Object> columnTranslateMap;
                for(SysDictionary sysDictionary : dicts){
                    if(sysDictionary.getDicClass().contains(object.getClass().getSimpleName())
                            && property.equals(sysDictionary.getDicProperty())){
                        //类型转换,都换成String类型,方便比较
                        String valueStr = value.toString();
                        //数据转换
                        if( valueStr.equals(sysDictionary.getDicValue()) ){
                            columnTranslateMap = new HashMap<>();
                            columnTranslateMap.put("code",sysDictionary.getDicValue());
                            columnTranslateMap.put("text",sysDictionary.getDicName());
                            value = columnTranslateMap;
                        }
                    }
                }

                return value;
            }
        };
        fastJsonConfig.setSerializeFilters(valueFilter);
        converter.setFastJsonConfig(fastJsonConfig);

        //处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        converter.setSupportedMediaTypes(fastMediaTypes);

        return converter;
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("只在服务启动时加载一次!");
        SysDictionaryExample sysDictionaryExample = new SysDictionaryExample();
        dicts = sysDictionaryMapper.selectByExample(sysDictionaryExample);
    }
}