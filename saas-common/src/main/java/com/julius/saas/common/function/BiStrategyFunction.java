package com.julius.saas.common.function;

import java.util.function.BiFunction;

/**
 * @author julius zhou
 * @date 6/2/22 10:14 AM
 * <p>
 *     扩展函数式接口 {@link BiFunction} <br/>
 *     这里可以增加一些 <red>default</red>方法进行扩展 <br/>
 * </p>
 * @see BiFunction
 **/
@FunctionalInterface
public interface BiStrategyFunction<T,U,K,R> extends BiFunction<T,U,R>{


    /**
     * 默认校验方法，默认返回true，由实现类重写实现
     * @param k
     * @return boolean
     */
    default boolean test(K k){
        return true;
    }
}
