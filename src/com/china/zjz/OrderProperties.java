package com.china.zjz;

import java.util.*;

/**
 * Created by lxk on 2017/5/2
 */
class OrderedProperties extends Properties {
    private static final long serialVersionUID = -4627607243846121965L;

    /**
     * ��ΪLinkedHashSet�������ԣ�key�ڵ���put()��ʱ�򣬴�ŵ�����Ҳ������
     */
    private final LinkedHashSet<Object> keys = new LinkedHashSet<>();

    @Override
    public Enumeration<Object> keys() {
        return Collections.enumeration(keys);
    }

    /**
     * ��put��ʱ��ֻ�ǰ�key����Ĵ浽{@link OrderedProperties#keys}
     * ȡֵ��ʱ�򣬸��������keys�����������ȡ������value
     * ��Ȼ���ø����put����,Ҳ����key value ��ֵ�Ի��Ǵ���hashTable��.
     * ֻ�����ڶ��˸���key������{@link OrderedProperties#keys}
     */
    @Override
    public Object put(Object key, Object value) {
        keys.add(key);
        return super.put(key, value);
    }

    /**
     * ��Ϊ��д������������ڣ���ʽһ����ʱ��,���������
     * {@link MainOrder#printProp}
     */
    @Override
    public Set<String> stringPropertyNames() {
        Set<String> set = new LinkedHashSet<>();
        for (Object key : this.keys) {
            set.add((String) key);
        }
        return set;
    }

    /**
     * ��Ϊ��д������������ڣ���ʽ������ʱ��,���������
     * {@link MainOrder#printProp}
     */
    @Override
    public Set<Object> keySet() {
        return keys;
    }

    //����Ͳ����������ˣ���Ϊ�漰��HashTable�ڲ��ࣺEntrySet�����ø�д��
    //public LinkedHashSet<Map.Entry<Object, Object>> entrySet() {
    //    LinkedHashSet<Map.Entry<Object, Object>> entrySet = new LinkedHashSet<>();
    //    for (Object key : keys) {
    //
    //    }
    //    return entrySet;
    //}

    /**
     * ��Ϊ��д������������ڣ���ʽ�ģ���ʱ��,���������
     * {@link MainOrder#printProp}
     */
    @Override
    public Enumeration<?> propertyNames() {
        return Collections.enumeration(keys);
    }
}
